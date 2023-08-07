package com.board.entity;

import com.board.dto.ReplyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만듦
@Builder
@Entity
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 400)
    private String content;

    @ManyToOne // 연관관계 형성
    @JoinColumn(name = "boardId")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계 형
    @JoinColumn(name = "userId")
    private User user;

    public ReplyDto toDto() {
        return ReplyDto.builder()
                .id(id)
                .content(content)
                .board(board)
                .user(user)
                .build();
    }
}
