package springc1.clonecoding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springc1.clonecoding.controller.request.ProductRequest;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.service.ProductService;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    //상품 등록
    @PostMapping("/api/product")
    public ResponseDto<?> createProduct(@RequestBody ProductRequest request,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
       return productService.createProduct(request, userDetails);
    }

    //상품 수정
    @Transactional
    @PutMapping("/api/product/{productId}")
    public ResponseDto<?> updateProduct(@RequestBody ProductRequest request,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                      @PathVariable Long productId){

       return productService.updateProduct(request, userDetails , productId);
    }


    //상품 삭제
    @Transactional
    @DeleteMapping("/api/product/{productId}")
    public ResponseDto<?> deleteProduct(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return productService.deleteProduct(productId, userDetails);
    }


    //상품 상세 조회
    @GetMapping("/api/product/id/{productId}")
    public ResponseDto<?> getLocalProduct(@PathVariable Long productId){
        return productService.getLocalProduct(productId);
    }

    //상품 전체 조회 (특정 지역)
    @GetMapping("/api/product/{location}")
    public ResponseDto<?> getLocationProducts(@PathVariable String location){
        return productService.getLocationProducts(location);
    }

    @GetMapping("/api/product")
    public ResponseDto<?> getAllProducts(){
        return productService.getAllProducts();
    }
}
