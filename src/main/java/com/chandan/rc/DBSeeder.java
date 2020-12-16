package com.chandan.rc;

import com.chandan.rc.documents.Deals;
import com.chandan.rc.documents.Product;
import com.chandan.rc.documents.ProductCategory;
import com.chandan.rc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    private HotelRepository hotelRepository;
//    private ProductCategoryRepository prodcutCategoryRepository;
    private ProductRepository productRepository;
//    private DealsRepository dealsRepository;
    private String[] defaultProducts = {"Sony Audio First", "Oats", "Mouse", "Puma T-Shirt", "Hello"};

    public DBSeeder(HotelRepository hotelRepository, ProductRepository productRepository) {
        this.hotelRepository = hotelRepository;
        this.productRepository = productRepository;
    }

    private List<Product> getSomeProductsForCategories(){
        List<Product> products = new ArrayList<>();
        for (int index = 0; index<4; index++) {
            System.out.println("Index------" + index);
            products.add(
                    new Product(defaultProducts[index], Integer.toString(index), (index+1) * 1000)
            );
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
            if(index % 2 == 0){
                deals.add(
                        new Deals(
                                (index+1)*10,
                                product.getId(),
                                System.currentTimeMillis() + 1200000
                        )
                );
            }
        }
        return deals;
    }

    @Override
    public void run(String... args) throws Exception {

        // Drop the existing collections
        this.hotelRepository.deleteAll();
        //this.prodcutCategoryRepository.deleteAll();
        //this.dealsRepository.deleteAll();
        this.productRepository.deleteAll();

        // Creating data for fresh insert
//        List<ProductCategory> productCategories = getSomeProductCategories();
//        // Adding data to the collections
//        this.prodcutCategoryRepository.saveAll(productCategories);

//        productCategories = this.prodcutCategoryRepository.findAll();
        List<Product> products = getSomeProductsForCategories();
        this.productRepository.saveAll(products);

//        products = this.productRepository.findAll();
//        List<Deals> deals = getSomeDealsForProducts(products);
//        this.dealsRepository.saveAll(deals);
    }
}
