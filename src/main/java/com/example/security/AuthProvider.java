package com.example.security;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        User user = userRepository.findUserByNameAndPass(username, password);
        if(user==null){
            throw new BadCredentialsException("Account not found.");
        }
        UserDetail dbUser = new UserDetail(user);
        //验证密码
        if (!password.equals(dbUser.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        //获取用户权限等级
        Collection<? extends GrantedAuthority> authorities = dbUser.getAuthorities();
        return new UsernamePasswordAuthenticationToken(dbUser, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
