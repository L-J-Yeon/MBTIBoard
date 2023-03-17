package ToyProject.MBTIBoard.controller;

import ToyProject.MBTIBoard.domain.Member;
import ToyProject.MBTIBoard.dto.MemberDto;
import ToyProject.MBTIBoard.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String memberRegister(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "member/memberRegisterForm";
    }

    @PostMapping(value = "/new")
    public String memberRegister(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/memberRegisterForm";
        }

        try {
            Member member = Member.registerMember(memberDto);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberRegisterForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/list")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMember();
        model.addAttribute("members", members);

        return "member/memberList";
    }
}
