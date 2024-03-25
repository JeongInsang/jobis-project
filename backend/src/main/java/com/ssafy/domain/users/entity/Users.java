package com.ssafy.domain.users.entity;

import com.ssafy.domain.users.dto.UserDto;
import com.ssafy.domain.users.dto.request.RegisterRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * UserDetails 인터페이스를 구현하여
 * 엔티티객체 자체를 인증객체로 활용
 * 
 */
@Entity
@Table(name = "users")
@Getter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;


    public static Users createUser(RegisterRequest request) {
        Users user = new Users();
        user.email = request.getEmail();;
        user.password = request.getPassword();
        return user;
    }

    public UserDto toUserDto(Users users) {
        return UserDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // 계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 자격 증명 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() {  // 계정 활성화 여부
        return true;
    }
}
