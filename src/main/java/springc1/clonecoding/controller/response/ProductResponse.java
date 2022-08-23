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


}
