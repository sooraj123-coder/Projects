package com.sooraj.Shop2Day.shopsite.Controller;

import com.sooraj.Shop2Day.shopsite.Entity.Cart;
import com.sooraj.Shop2Day.shopsite.Entity.Product;
//import com.sooraj.Shop2Day.shopsite.Global.GlobalData;
import com.sooraj.Shop2Day.shopsite.Global.GlobalData;
import com.sooraj.Shop2Day.shopsite.Service.CartService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalData.findUserName();
        //write code for adding the product to cart of the user
        //we need Products Id and the user email
        Cart newCart = new Cart();
        newCart.setCartEmail(GlobalData.user_email);
        newCart.setProductId(id);
        cartService.addToCart(newCart);
        GlobalData.cart.add(productService.getProductById(id).get());
        System.out.println("Product added ");
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String addToCart(Model model){
        GlobalData.cart.clear();
        GlobalData.findUserName();
        cartService.getCartItems();
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPprice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeItemFromCart(@PathVariable int index){
        Long productId = GlobalData.cart.get(index).getPid();
        Cart oldCart=new Cart();
        oldCart.setProductId(productId);
        oldCart.setCartEmail(GlobalData.user_email);
        GlobalData.cart.remove(index);
        cartService.removeFromCart(oldCart);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checout(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPprice).sum());
        return "checkout";
    }

}
