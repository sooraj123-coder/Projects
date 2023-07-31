package com.sooraj.Shop2Day.shopsite.Controller;

import com.sooraj.Shop2Day.shopsite.Service.CategoryService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private CategoryService theCategoryService;

    @Autowired
    private ProductService theProductService;

    @GetMapping({"/","/home"})
    public String home(){
        return "index";
    }


    @GetMapping("/shop")
    public String shop(Model theModel){
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        theModel.addAttribute("products",theProductService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String getProductByCategory(Model theModel, @PathVariable Integer id){
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        theModel.addAttribute("products",theProductService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProducts(Model theModel, @PathVariable Long id){
        theModel.addAttribute("product",theProductService.getProductById(id).get());
        return "viewProduct";
    }
}
