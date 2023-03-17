package ToyProject.MBTIBoard.domain;

import ToyProject.MBTIBoard.constant.Mbti;
import ToyProject.MBTIBoard.constant.Role;
import ToyProject.MBTIBoard.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Mbti mbti;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member registerMember(MemberDto memberDto) {
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setMbti(memberDto.getMbti());
        member.setRole(Role.MEMBER);

        return member;
    }
}
