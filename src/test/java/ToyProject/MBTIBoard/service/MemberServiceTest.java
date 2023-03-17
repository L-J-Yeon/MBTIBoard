package ToyProject.MBTIBoard.service;

import ToyProject.MBTIBoard.constant.Mbti;
import ToyProject.MBTIBoard.domain.Member;
import ToyProject.MBTIBoard.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    public Member registerMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("test@test.com");
        memberDto.setPassword("testpassword");
        memberDto.setName("test");
        memberDto.setMbti(Mbti.ENFJ);
        return Member.registerMember(memberDto);
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMemberTest() {
        Member member = registerMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getMbti(), savedMember.getMbti());
        assertEquals(member.getRole(), savedMember.getRole());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest() {
        Member member1 = registerMember();
        Member member2 = registerMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입한 회원입니다", e.getMessage());
    }
}
