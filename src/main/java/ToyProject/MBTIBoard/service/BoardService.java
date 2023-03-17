package ToyProject.MBTIBoard.service;

import ToyProject.MBTIBoard.domain.Board;
import ToyProject.MBTIBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    public Board boardDetail(Long id) {
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }
}
