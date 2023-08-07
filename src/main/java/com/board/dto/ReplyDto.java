package com.board.dto;

import com.board.entity.Board;
import com.board.entity.Reply;
import com.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    private Long id;
    private String content;
    private Board board;
    private User user;

    //request 용도
    public Reply toEntity() {
        return Reply.builder()
                .id(id)
                .content(content)
                .board(board)
                .user(user)
                .build();
    }

    //response 용도
    public ReplyDto toDto(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.board = reply.getBoard();
        this.user = reply.getUser();
        return this;
    }

}
