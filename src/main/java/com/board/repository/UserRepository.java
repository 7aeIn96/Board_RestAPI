package com.board.repository;

import com.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //PrincipalDetailService 에서 username 으로 찾기 위함
    User findByUsername(String username);
}