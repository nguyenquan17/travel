package com.example.travel.services.impl;

import com.example.travel.entity.RefreshToken;
import com.example.travel.exception.TokenRefreshException;
import com.example.travel.repository.IRefreshTokenRepository;
import com.example.travel.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final static long refreshTokenDurationMs = 86400000 ; //86400000

    @Autowired
    private IRefreshTokenRepository iRefreshTokenRepository;

    @Autowired
    private IUserRepository iUserRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return iRefreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(int userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(iUserRepository.findById(userId).orElse(null));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = iRefreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            iRefreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new sign in request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(int userId) {
        return iRefreshTokenRepository.deleteByUser(iUserRepository.findById(userId).orElse(null));
    }
}
