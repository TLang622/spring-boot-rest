package com.example.security;

import com.example.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class UserDetail extends User implements UserDetails {

    UserDetail() {
    }

    public UserDetail(User user) {
        super.setId(user.getId());
        super.setName(user.getName());
        super.setPassword(user.getPassword());
        //user可以增加一个权限等级的字段
        //super.setAuthority(user.getAuthority());
    }

    //用户权限等级
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        //用户权限这里给了个普通用户权限0，也可以注册时候设置用户权限等级，然后存在数据库，然后这里读取数据库super.getAuthority().toString())
        auth.add(new SimpleGrantedAuthority("0"));
        return auth;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
