package springc1.clonecoding.controller.response;


import lombok.Getter;
import springc1.clonecoding.domain.Product;

@Getter
public class ProductResponse {


    private String name;

    private Long price;

    private String imgUrl;

    private String content;



    public ProductResponse(Product save) {
    }
}
