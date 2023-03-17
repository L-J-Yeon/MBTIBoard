package ToyProject.MBTIBoard.controller;

import ToyProject.MBTIBoard.domain.Board;
import ToyProject.MBTIBoard.dto.BoardDto;
import ToyProject.MBTIBoard.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping(value = "/write")
    public String boardWrite(Model model) {
        model.addAttribute("boardDto", new BoardDto());

        return "board/boardWrite";
    }

    @PostMapping(value = "/write")
    public String boardWrite(@Valid BoardDto boardDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "board/boardWrite";
        }

        try {
            Board board = Board.registerBoard(boardDto);
            boardService.saveBoard(board);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "board/boardWrite";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/list")
    public String boardList(Model model) {
        List<Board> boards = boardService.findAllBoard();
        model.addAttribute("boards", boards);

        return "board/boardList";
    }

    @GetMapping(value = "/detail/{id}")
    public String boardDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/boardDetail";
    }

    @GetMapping(value = "/delete")
    public String boardDelete(Long id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping(value = "/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/boardUpdate";
    }

    @PostMapping(value = "/modify/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board) {
        Board boardTemp = boardService.boardDetail(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setWriter(board.getWriter());
        boardTemp.setContent(board.getContent());
        boardService.saveBoard(boardTemp);

        return "redirect:/board/list";
    }
}
