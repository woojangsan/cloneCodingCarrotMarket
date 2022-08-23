package springc1.clonecoding.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springc1.clonecoding.domain.Comment;
import springc1.clonecoding.domain.Member;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
