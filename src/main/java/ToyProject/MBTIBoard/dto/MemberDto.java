package ToyProject.MBTIBoard.dto;

import ToyProject.MBTIBoard.constant.Mbti;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {
    @NotEmpty(message = "이메일은 필수 입력사항입니다")
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력사항입니다")
    private String password;

    @NotEmpty(message = "이름은 필수 입력사항입니다")
    private String name;
    
    private Mbti mbti;
}
