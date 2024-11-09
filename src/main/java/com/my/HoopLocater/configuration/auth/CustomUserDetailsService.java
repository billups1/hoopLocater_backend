package com.my.HoopLocater.configuration.auth;

import com.my.HoopLocater.common.exception.CustomAuthException;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByLoginId(username).map(this::createUserDetails).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });
    }

    private UserDetails createUserDetails(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), grantedAuthorities);
    }
}
