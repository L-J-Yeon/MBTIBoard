package ToyProject.MBTIBoard.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDto {
    private Long id;

    @NotEmpty(message = "제목을 입력하세요")
    private String title;

    @NotEmpty(message = "작성자를 입력하세요")
    private String writer;

    @Lob
    private String content;

}
