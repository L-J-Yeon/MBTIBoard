package ToyProject.MBTIBoard.repository;

import ToyProject.MBTIBoard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    List<Member> findAll();
}
