package springc1.clonecoding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springc1.clonecoding.controller.request.ProductRequest;
import springc1.clonecoding.controller.response.ProductResponse;
import springc1.clonecoding.controller.response.ResponseDto;
import springc1.clonecoding.service.ProductServiceImpl;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    //상품 등록
    @PostMapping("/api/product")
    public ResponseDto<ProductResponse> createProduct(@RequestBody ProductRequest request){
       return new ResponseDto<>(true,productService.createProduct(request),null);
    }
}
