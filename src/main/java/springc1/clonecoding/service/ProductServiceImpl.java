package springc1.clonecoding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springc1.clonecoding.controller.request.ProductRequest;
import springc1.clonecoding.controller.response.*;
import springc1.clonecoding.domain.ImgProduct;
import springc1.clonecoding.domain.Member;
import springc1.clonecoding.domain.Product;
import springc1.clonecoding.domain.UserDetailsImpl;
import springc1.clonecoding.repository.ImgProductRepository;
import springc1.clonecoding.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    private final ImgProductRepository imgProductRepository;

    //상품 등록
    public ResponseDto<?> createProduct(ProductRequest request, UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        //멤버 정보 가져오기
        Product product = new Product(request, member);
        //상품 생성하기
        productRepository.save(product);
        //상품 저장하기
        List<String> imgProductList = request.getImgProductList();
        for (String imgUrl : imgProductList){
            ImgProduct imgProduct = new ImgProduct(product, imgUrl);
            imgProductRepository.save(imgProduct);
        }
        //이미지 가져와서 저장하기

        return ResponseDto.success("success");
    }


    //상품 업데이트
    @Transactional
    public ResponseDto<?> updateProduct(ProductRequest request, UserDetailsImpl userDetails, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        //id로 상품 찾아오기
        List<ImgProduct> imgProductList = imgProductRepository.findAllByProduct(product);
        //imgProductList로 이미지 찾아오기
        Member member = userDetails.getMember();
        //member 정보
        productMemeber(product, member);
        //상품 등록자 일치 여부
        product.update(request);
        //상품 업데이트
        //상품 저장
        System.out.println("ㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅ");
        for(ImgProduct imgProduct : imgProductList){
            System.out.println("ㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂㅂ");
            imgProduct.setImgUrl(imgProduct.getImgUrl());
            System.out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            product.update(imgProduct);
            System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
            imgProductRepository.save(new ImgProduct(imgProduct));
            System.out.println("ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ");
        }
        productRepository.save(product);
        System.out.println("ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ");
        //이미지 수정 후 저장
            return ResponseDto.success("success");
    }


    //상품 삭제
    public ResponseDto<?> deleteProduct(Long productId, UserDetailsImpl userDetails) {
        Product product = productRepository.findById(productId).orElseThrow();
        Member member = userDetails.getMember();

        productMemeber(product, member);

        return ResponseDto.success("success");
    }


    //상품 단일 조회 (비로그인도 가능)
    @Transactional
    public ResponseDto<?> getLocalProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");

        List<ImgProduct> imgProductList = imgProductRepository.findAllByProduct(product);
        System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
        List<ImgProduct> imgProductList1 = new ArrayList<>();
        for (ImgProduct imgProduct : imgProductList){
            ImgProduct imgProduct1 = new ImgProduct(imgProduct);
            imgProductList1.add(imgProduct1);
        }
        System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");


        return ResponseDto.success(new AllProductsResponse(product, imgProductList1));
    }


    //상품 전체 조회 (특정 지역)
    @Transactional
    public ResponseDto<?> getLocationProducts(String location){
        List<Product> products = productRepository.findAllByLocation(location);
        List<AllProductsResponse> allProductsResponses = new ArrayList<>();
        for(Product product : products){
            AllProductsResponse allProductsResponse = new AllProductsResponse(product);
            allProductsResponses.add(allProductsResponse);
        }
        return ResponseDto.success(allProductsResponses);
    }


    //상품 전체 조회
    public ResponseDto<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<AllProductsResponse> allProductsResponses = new ArrayList<>();
        for (Product product : products){
            AllProductsResponse allProductsResponse = new AllProductsResponse(product);
            allProductsResponses.add(allProductsResponse);
        }
        return ResponseDto.success(allProductsResponses);
    }


    //권한 여부 검증
    public void productMemeber(Product product, Member member){
        if(!product.getMember().equals(member)){
            throw new IllegalArgumentException("상품 게시글 작성자가 아닙니다.");

        }
    }
}
