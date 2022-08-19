package springc1.clonecoding.service;

import org.springframework.stereotype.Service;
import springc1.clonecoding.controller.request.ProductRequest;
import springc1.clonecoding.controller.response.ProductResponse;
import springc1.clonecoding.domain.Product;
import springc1.clonecoding.repository.ProductRepository;

@Service
public class ProductServiceImpl {

    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .content(request.getContent())
                .build();

        return new ProductResponse(productRepository.save(product));

    }
}
