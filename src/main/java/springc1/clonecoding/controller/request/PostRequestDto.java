package springc1.clonecoding.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import springc1.clonecoding.domain.ImgPost;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private List<ImgPost> imgPostList;

}
