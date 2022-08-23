package springc1.clonecoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springc1.clonecoding.domain.ImgPost;

public interface ImgPostRepository extends JpaRepository<ImgPost, Long> {
}
