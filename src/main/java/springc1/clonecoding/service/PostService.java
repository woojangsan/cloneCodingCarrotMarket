package springc1.clonecoding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springc1.clonecoding.controller.request.PostRequestDto;
import springc1.clonecoding.controller.request.imgPostDto;
import springc1.clonecoding.controller.response.PostResponseDto;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.ImgPost;
import springc1.clonecoding.domain.Member;
import springc1.clonecoding.domain.Post;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.repository.ImgPostRepository;
import springc1.clonecoding.repository.JPAInterface.PostList;
import springc1.clonecoding.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImgPostRepository imgPostRepository;

    @Transactional
    public ResponseDto<?> createPost(PostRequestDto postRequestDto, UserDetailsImpl userDetailsImpl) {
        Post post = new Post(postRequestDto, userDetailsImpl.getMember());

        postRepository.save(post);

        List<ImgPost> imgPostList = postRequestDto.getImgPostList();

        for (ImgPost img : imgPostList){
            img.setPost(post);
            imgPostRepository.save(img);
        }


        return ResponseDto.success("success");
    }
    @Transactional
    public ResponseDto<?> editPost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetailsImpl) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        validatePost(userDetailsImpl.getMember(), post);

        post.update(postRequestDto);
        return ResponseDto.success("success");
    }

    @Transactional
    public ResponseDto<?> deletePost(Long postId, UserDetailsImpl userDetailsImpl) {
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        validatePost(userDetailsImpl.getMember(), post);
        postRepository.delete(post);
        return ResponseDto.success("success");
    }

    @Transactional
    public ResponseDto<?> getAllLocalPosts(String location) {
        List<PostList> postList = postRepository.findAllByLocation(location);
        return ResponseDto.success(postList);
    }

    @Transactional
    public ResponseDto<?> getAllPosts() {

        List<PostList> postList = postRepository.findAllCustom();
        return ResponseDto.success(postList);
    }

    @Transactional
    public ResponseDto<?> getPostDetails(Long postId) {
        Post post = postRepository.findById(postId).get();
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return ResponseDto.success(postResponseDto);
    }

    public void validatePost(Member member, Post post){
        if(!member.getUsername().equals(post.getMember().getUsername())){
            throw new IllegalArgumentException("게시글을 삭제할 권한이 없습니다.");
        }
    }

}
