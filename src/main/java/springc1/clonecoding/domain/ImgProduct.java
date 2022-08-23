package springc1.clonecoding.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImgProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


    public ImgProduct(Product product, String imgUrl) {
        this.imgUrl = imgUrl;
        this.product = product;
    }

    public ImgProduct(ImgProduct imgProduct) {
        this.id = imgProduct.getId();
        this.imgUrl = imgProduct.getImgUrl();
        this.product = imgProduct.getProduct();

        //JsonIgnore로 product는 안뜨게했고 imgUrl을 수정하면 수정한 값으로 가져와야 하는데 거기까진 일단 모르겠음
        //그래도 이미지url 리스트로 가져오게는 성공
    }


}
