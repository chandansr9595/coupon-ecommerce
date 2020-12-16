package com.chandan.rc.contollers;

import com.chandan.rc.documents.ProductCategory;
import com.chandan.rc.repository.ProductCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping("/all")
    public List<ProductCategory> getAll(){
        return this.productCategoryRepository.findAll();
    }

    @PostMapping
    public void createProductCategory(@RequestBody ProductCategory productCategory){
        this.productCategoryRepository.insert(productCategory);
    }
}
