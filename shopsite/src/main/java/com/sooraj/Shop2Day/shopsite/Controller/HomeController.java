package com.sooraj.Shop2Day.shopsite.Controller;

import com.sooraj.Shop2Day.shopsite.Global.GlobalData;
import com.sooraj.Shop2Day.shopsite.Service.CartService;
import com.sooraj.Shop2Day.shopsite.Service.CategoryService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private CategoryService theCategoryService;

    @Autowired
    private ProductService theProductService;

    @Autowired
    CartService cartService;

    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        //return "index";
       // Long count = GlobalData.user_role.stream().filter(e->e.equals("Admin")).count();
        return "redirect:/shop";

    }


    @GetMapping("/shop")
    public String shop(Model theModel){
        GlobalData.cart.clear();
        GlobalData.findUserName();
        cartService.getCartItems();
        //System.out.println("Username = "+GlobalData.user_email);
        theModel.addAttribute("cartCount",GlobalData.cart.size());
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        theModel.addAttribute("products",theProductService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String getProductByCategory(Model theModel, @PathVariable Integer id){
        theModel.addAttribute("cartCount",GlobalData.cart.size());
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        theModel.addAttribute("products",theProductService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProducts(Model theModel, @PathVariable Long id){
        theModel.addAttribute("cartCount",GlobalData.cart.size());
        theModel.addAttribute("product",theProductService.getProductById(id).get());
        return "viewProduct";
    }

    @GetMapping("/access-denied")
    public String showAcessDeniedPage(){
        return "access-denied";
    }

//    @PreAuthorize("hasRole('ROLE_Admin')")
//    @GetMapping("/test-auth")
//    public String showpage(){
//        return "shop";
//    }
}
