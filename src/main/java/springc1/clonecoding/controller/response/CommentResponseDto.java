package springc1.clonecoding.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long postId;
    private String content;

}
