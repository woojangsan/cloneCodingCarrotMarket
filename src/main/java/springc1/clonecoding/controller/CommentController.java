package springc1.clonecoding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springc1.clonecoding.controller.request.CommentRequestDto;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/api/comment")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto commentRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return commentService.createComment(commentRequestDto, userDetailsImpl);
    }

    // 댓글 수정
    @PutMapping("/api/comment/{commentId}")
    public ResponseDto<?> editComent(@RequestBody CommentRequestDto commentRequestDto,
                                     @PathVariable Long commentId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return commentService.editComment(commentRequestDto, commentId, userDetailsImpl);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return commentService.deleteComment(commentId, userDetailsImpl);
    }



}
