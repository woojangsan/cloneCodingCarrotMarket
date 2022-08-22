package springc1.clonecoding.controller.response;


import lombok.Getter;
import springc1.clonecoding.domain.ImgProduct;
import springc1.clonecoding.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProductResponse {

    private Long id;

    private String nickname;
    private String name;

    private Long price;


    private List<ImgProduct> imgProductList;

    private String content;

    private String location;

    private LocalDateTime createdAt;


    public ProductResponse(Product save) {
        this.id = save.getProduct_id();
        this.nickname = save.getNickname();
        this.content = save.getContent();
        this.price = save.getPrice();
        this.name = save.getName();
        this.location = save.getLocation();
        this.createdAt = save.getMember().getCreatedAt();
        this.imgProductList = save.getImgProductList();

    }


    public ProductResponse(Product product, Product productImg) {
        this.imgProductList = productImg.getImgProductList();
    }

}
