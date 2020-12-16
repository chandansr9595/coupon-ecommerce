package com.chandan.rc;

import com.chandan.rc.documents.Deals;
import com.chandan.rc.documents.Product;
import com.chandan.rc.documents.ProductCategory;
import com.chandan.rc.repository.DealsRepository;
import com.chandan.rc.repository.ProductCategoryRepository;
import com.chandan.rc.repository.ProductRepository;
import com.chandan.rc.utils.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    private ProductCategoryRepository prodcutCategoryRepository;
    private ProductRepository productRepository;
    private DealsRepository dealsRepository;
    private String[] defaultProducts = {
            "Sony Audio First",
            "JBL Speaker",
            "Oats",
            "Wheat floor",
            "Mouse",
            "Key Board",
            "Puma T-Shirt",
            "HRX Track suite"};

    public DBSeeder(ProductCategoryRepository productCategoryRepository,
                    ProductRepository productRepository,
                    DealsRepository dealsRepository) {
        this.prodcutCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.dealsRepository = dealsRepository;
    }

    private List<Product> getSomeProductsForCategories(List<ProductCategory> productCategories){
        List<Product> products = new ArrayList<>();
        int index = 0;
        for (ProductCategory productCategory: productCategories) {
            for(int j=0; j<2;j++){
                products.add(new Product(
                        defaultProducts[index++],
                        productCategory.getId(),
                        (index) * 1000)
                );
            }
        }
        return products;
    }

    private List<ProductCategory> getSomeProductCategories(){
        ProductCategory electronics = new ProductCategory("Electronics");
        ProductCategory grocery = new ProductCategory("Grocery & Gourmet Foods");
        ProductCategory computers = new ProductCategory("Computers & Accessories");
        ProductCategory clothing = new ProductCategory("Clothing & Accessories");
        return Arrays.asList(electronics, grocery, computers, clothing);
    }

    private List<Deals> getSomeDealsForProducts(List<Product> products){
        List<Deals> deals = new ArrayList<>();
        int index = 0;
        for (Product product: products) {
            System.out.println(index);
            if(index % 2 == 0){
                deals.add(new Deals(
                                Utils.getDealPercentage(),
                                product.getId(),
                                Utils.getDealAvailableTill()
                        )
                );
            }
            index++;
        }
        return deals;
    }

    @Override
    public void run(String... args) throws Exception {

        // Drop the existing collections
        this.prodcutCategoryRepository.deleteAll();
        this.dealsRepository.deleteAll();
        this.productRepository.deleteAll();

        // Creating data for fresh insert
        List<ProductCategory> productCategories = getSomeProductCategories();
        // Adding data to the collections
        this.prodcutCategoryRepository.saveAll(productCategories);

        productCategories = this.prodcutCategoryRepository.findAll();
        List<Product> products = getSomeProductsForCategories(productCategories);
        this.productRepository.saveAll(products);
    }
}
