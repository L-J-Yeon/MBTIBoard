package ToyProject.MBTIBoard.domain;

import ToyProject.MBTIBoard.dto.BoardDto;
import ToyProject.MBTIBoard.service.BoardService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String writer;
    private String content;

    public static Board registerBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setWriter(boardDto.getWriter());
        board.setContent(boardDto.getContent());

        return board;
    }
}
