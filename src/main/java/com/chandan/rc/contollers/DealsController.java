package com.chandan.rc.contollers;

import com.chandan.rc.documents.Deals;
import com.chandan.rc.repository.DealsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealsController {
    private DealsRepository dealsRepository;

    public DealsController(DealsRepository dealsRepository) {
        this.dealsRepository = dealsRepository;
    }

    @GetMapping("/all")
    public List<Deals> getAll(){
        return this.dealsRepository.findAll();
    }

    @GetMapping("/allByProductId")
    public List<Deals> getByProductIds(@RequestBody  List<String> productIds){
        return this.dealsRepository.findAllByProductId(productIds);
    }
}
