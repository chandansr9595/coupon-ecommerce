package com.chandan.rc.shedulers;

import com.chandan.rc.constants.DealsConstants;
import com.chandan.rc.documents.Deals;
import com.chandan.rc.documents.Product;
import com.chandan.rc.repository.DealsRepository;
import com.chandan.rc.repository.ProductRepository;
import com.chandan.rc.utils.Utils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class DealsScheduler {

    private DealsRepository dealsRepository;
    private ProductRepository productRepository;

    public DealsScheduler(DealsRepository dealsRepository, ProductRepository productRepository) {
        this.dealsRepository = dealsRepository;
        this.productRepository = productRepository;
    }

    // Change it to get the fixedRate for scheduler from properties file
    @Scheduled(
            fixedRateString = "#{@applicationPropertyService.getApplicationProperty()}",
            initialDelay = 1000
    )
    public void createDeals(){
        dealsRepository.deleteAll();
        dealsRepository.saveAll(getTheDealsForProducts());
        System.out.println("Deals created");
    }

    private List<Deals> getTheDealsForProducts(){
        List<Product> productList = productRepository.findAll();
        List<Integer> randomProductsToAddDeals = Utils.getListOfRandomNumbers(productList.size());
        List<Deals> dealsList = new ArrayList<>();
        for(int i=0; i<productList.size(); i++){
            if(randomProductsToAddDeals.get(i) == 1){
                dealsList.add(
                        new Deals(
                                Utils.getDealPercentage(),
                                productList.get(i).getId(),
                                Utils.getDealAvailableTill()
                        )
                );
            }
        }
        return dealsList;
    }

}

@Service
class ApplicationPropertyService {
    public String getApplicationProperty(){
        return Long.toString(DealsConstants.DEAL_AVAILABLE_TILL);
    }
}