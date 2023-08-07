package com.board.entity;

import com.board.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity // User 클래스가 MySQL에 자동으로 테이블 생성
public class User extends BaseTimeEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY 전략은 기본 키 생성을 프로젝트에 연결된 데이터베이스에 위임하는 전략
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //DB는 RoleType에 대한 정보를 모르기에 각 Enum 이름(String)을 컬럼에 저장
    private RoleType role;

    private String provider;
    private String providerId;


    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateRole(RoleType role) {
        this.role = role;
    }

    public UserResponseDto toDto() {
        return UserResponseDto.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }

    @Builder
    public User(long id, String username, String password, String email, RoleType role, String provider, String providerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
