package springc1.clonecoding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springc1.clonecoding.controller.request.CommentRequestDto;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.Comment;
import springc1.clonecoding.domain.Member;
import springc1.clonecoding.domain.Post;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.repository.CommentRepository;
import springc1.clonecoding.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseDto<?> createComment(CommentRequestDto commentDto, UserDetailsImpl userDetailsImpl) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        commentRepository.save(new Comment(commentDto, userDetailsImpl.getMember(), post));

        return ResponseDto.success("success");
    }

    @Transactional
    public ResponseDto<?> editComment(CommentRequestDto commentDto, Long commentId, UserDetailsImpl userDetailsImpl) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        validateComment(userDetailsImpl.getMember(), comment);
        comment.update(commentDto);
        return ResponseDto.success("success");

    }

    @Transactional
    public ResponseDto<?> deleteComment(Long commentId, UserDetailsImpl userDetailsImpl) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        validateComment(userDetailsImpl.getMember(), comment);
        commentRepository.delete(comment);
        return ResponseDto.success("success");
    }

    public void validateComment(Member member, Comment comment){
        if(!member.getUsername().equals(comment.getMember().getUsername())){
            throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
        }
    }


}
