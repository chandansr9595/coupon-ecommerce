package com.chandan.rc.contollers;

import com.chandan.rc.documents.Deals;
import com.chandan.rc.repository.DealsRepository;
import com.chandan.rc.repository.ProductRepository;
import com.chandan.rc.documents.Product;
import com.chandan.rc.utils.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;
    private DealsRepository dealsRepository;

    public ProductController(ProductRepository productRepository, DealsRepository dealsRepository) {
        this.productRepository = productRepository;
        this.dealsRepository = dealsRepository;
    }

    @GetMapping("/all")
    public List<ProductWithDeals> getAll(){
        List<Product> productList = this.productRepository.findAll();
        return getProductsAlongWithDeals(productList);
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
            if(Utils.isNotBlank(product.getProductName())){
                existingProduct.setProductName(product.getProductName());
            }
            if(product.getPrice() != null){
                existingProduct.setPrice(product.getPrice());
            }
            this.productRepository.save(existingProduct);
        });
    }

    @GetMapping("/category/{id}")
    public List<ProductWithDeals> getByProductCategory(@PathVariable("id") String id) {
        List<Product> productList = this.productRepository.findByCategoryId(id);
        return  getProductsAlongWithDeals(productList);
    }

    private Map<String, Integer> getProductDealsMapping(){
        List<Deals> dealsList = this.dealsRepository.findAll();
        Map<String, Integer> productToDealsMapping = new HashMap<>();
        for (Deals deal: dealsList) {
            productToDealsMapping.put(deal.getProductId(), deal.getPercentageOfOffer());
        }
        return productToDealsMapping;
    }

    private List<ProductWithDeals> getProductDeals(List<Product> productList, Map<String, Integer> productToDealsMapping){
        List<ProductWithDeals> productWithDeals = new ArrayList<>();
        for (Product product: productList) {
            if(productToDealsMapping.containsKey(product.getId())){
                productWithDeals.add(new ProductWithDeals(product, productToDealsMapping.get(product.getId())));
            } else {
                productWithDeals.add(new ProductWithDeals(product, 0));
            }
        }
        return productWithDeals;
    }

    class ProductWithDeals {
        Product product;
        int dealPercentage;

        public ProductWithDeals(Product product, int dealPercentage) {
            this.product = product;
            this.dealPercentage = dealPercentage;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getDealPercentage() {
            return dealPercentage;
        }

        public void setDealPercentage(int dealPercentage) {
            this.dealPercentage = dealPercentage;
        }
    }

    private List<ProductWithDeals> getProductsAlongWithDeals(List<Product> productList){
        Map<String, Integer> productToDealsMapping = getProductDealsMapping();
        return getProductDeals(productList, productToDealsMapping);
    }
}
