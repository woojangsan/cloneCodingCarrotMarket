package springc1.clonecoding.controller.request;


import lombok.Getter;
import springc1.clonecoding.domain.ImgProduct;

import java.util.List;

@Getter
public class ProductRequest {

    private String name;

    private Long price;

    private List<ImgProduct> imgProductList;

    private String content;
}
