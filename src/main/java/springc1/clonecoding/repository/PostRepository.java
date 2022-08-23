package springc1.clonecoding.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springc1.clonecoding.domain.Post;
import springc1.clonecoding.repository.JPAInterface.PostList;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostList> findAllByLocation(String location);

    /*
    @Query(value="SELECT * FROM post", nativeQuery = true)
    List<PostList> findAllCustom();

    @Query(value="SELECT * FROM post p JOIN img_post i WHERE p.id = i.post_id", nativeQuery = true)
    List<PostList> findAllCustom();
     */

    @Query(value="SELECT m FROM Post AS m")
    List<PostList> findAllCustom();


}

