package springc1.clonecoding.repository.JPAInterface;

import springc1.clonecoding.domain.ImgPost;

import java.util.List;

public interface PostList {
    Long getId();
    String getNickname();
    String getTitle();
    String getLocation();
    List<ImgPost> getImgPostList();
}