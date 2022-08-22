package springc1.clonecoding.controller.response;

import lombok.Getter;
import springc1.clonecoding.domain.ImgProduct;
import springc1.clonecoding.domain.Product;

import java.util.List;

@Getter
public class AllProductsResponse {

    private Long id;

    private String nickname;

    private String name;

    private Long price;

    private String location;

    private List<ImgProduct> imgProductList;

    public AllProductsResponse(Product product) {
        this.id = product.getId();
        this.nickname = product.getNickname();
        this.name = product.getName();
        this.price = product.getPrice();
        this.location = product.getLocation();
        this.imgProductList = product.getImgProductList();
    }


    public AllProductsResponse(Product product, List<ImgProduct> imgProductList1) {
        this.id = product.getId();
        this.nickname = product.getNickname();
        this.name = product.getName();
        this.price = product.getPrice();
        this.location = product.getLocation();
        this.imgProductList = imgProductList1;

    }
}
