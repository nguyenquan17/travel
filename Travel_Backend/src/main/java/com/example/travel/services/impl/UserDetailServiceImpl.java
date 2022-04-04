package com.example.travel.services.impl;

import com.example.travel.entity.User;
import com.example.travel.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

//    @Autowired
    private final IUserRepository iUserRepository;

    public UserDetailServiceImpl(IUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Check user tồn tại qua username
        User user = iUserRepository.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("Role: " + user.getRole().getNameRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList(user.getRole().getNameRole()));
    }
}
