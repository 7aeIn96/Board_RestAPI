package com.board.entity;

import com.board.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 자동으로 테이블 생성
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String category;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 사용 -<html> 태크 섞여 디자인
    //조회수
    @Column(nullable = false)
    private int views;

    //글쓴이
    //DB는 오브젝트 저장X-> FK사용, 자바는 오브젝트 저장 가능
    @ManyToOne(fetch = FetchType.LAZY) //EAGER=호출시 바로 로드
    @JoinColumn(name = "userId") //DB상 필드값은 userId로 설정
    private User user;

    @OneToMany(mappedBy = "board",
            fetch = FetchType.EAGER, //설계한 프로젝트 UI 구조상 '댓글 펼치기' 같은 지연 로딩이 아닌, 게시판에 바로 댓글이 보이는 것이기에 EAGER 전략
            cascade = CascadeType.REMOVE) //Board(게시판)삭제시 댓글도 함께 삭제
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replyList;


    public void updateBoard(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public BoardDto toDto() {
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .views(views)
                .user(user)
                .replyList(replyList)
                .build();
    }
}
