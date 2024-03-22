package com.ssafy.domain.users.entity;

import com.ssafy.domain.users.dto.UserDto;
import com.ssafy.domain.users.dto.request.RegisterRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class Users {

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

}
