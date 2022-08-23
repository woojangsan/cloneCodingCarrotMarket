package springc1.clonecoding.controller.response;

import lombok.*;
import springc1.clonecoding.domain.ImgPost;
import springc1.clonecoding.domain.Post;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String nickname;
    private String title;
    private String location;
    private List<ImgPost> imgPostList;

    public PostResponseDto(Post post) {
        this. nickname = post.getNickname();
        this.title = post.getTitle();
        this.location = post.getLocation();
        this.imgPostList = post.getImgPostList();
    }
}

// 게시글 전체 조회시에 필요
// 게시글 상세 조회는 바로 entity return.