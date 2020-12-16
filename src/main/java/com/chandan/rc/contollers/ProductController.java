package com.chandan.rc.contollers;

import com.chandan.rc.repository.ProductRepository;
import com.chandan.rc.documents.Product;
import com.chandan.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product){
        this.productRepository.insert(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String id){
        this.productRepository.deleteById(id);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        Optional<Product> optional = this.productRepository.findById(product.getId());
        optional.ifPresent(existingProduct -> {
            if(StringUtils.isNotBlank(product.getProductName())){
                existingProduct.setProductName(product.getProductName());
            }
            if(product.getPrice() != null){
                existingProduct.setPrice(product.getPrice());
            }
            this.productRepository.save(existingProduct);
        });
    }

//    @GetMapping("/{id}")
//    public Product getByProductCategory(@PathVariable("id") String id) {
//        Optional<Product> optional = Optional.ofNullable(this.productRepository.findByCategoryId(id));
//        AtomicReference<Product> atomicReference = new AtomicReference<>();
//        optional.ifPresent(existingProduct -> {
//            atomicReference.set(existingProduct);
//        });
//        return atomicReference.get();
//    }
}
