package com.example.travel.repository;

import com.example.travel.entity.RefreshToken;
import com.example.travel.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

//    @Override
    @NotNull
//    Optional<RefreshToken> findById(@NotNull Long id);
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
