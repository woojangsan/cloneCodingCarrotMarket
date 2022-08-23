package springc1.clonecoding.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import springc1.clonecoding.controller.request.ProductRequest;
import springc1.clonecoding.controller.response.ProductResponse;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String location;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ImgProduct> imgProductList;

    public Product(ProductRequest request, Member member) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.content = request.getContent();
        this.location = request.getLocation();
        this.member = member;
    }


    public void update(ProductRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.content = request.getContent();
        this.location = request.getLocation();
    }
}
