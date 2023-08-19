package com.sooraj.Shop2Day.shopsite.Global;

import com.sooraj.Shop2Day.shopsite.DAO.CartRepo;
import com.sooraj.Shop2Day.shopsite.Entity.Cart;
import com.sooraj.Shop2Day.shopsite.Entity.Product;
import com.sooraj.Shop2Day.shopsite.Service.CartService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import com.sun.jdi.LongValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    public static String user_email;
    public static Collection<? extends GrantedAuthority> user_role;


   static {
        cart = new ArrayList<>();
       findUserName();
   }
    public static void findUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            GlobalData.user_email = ((UserDetails)principal).getUsername();
            user_role=((UserDetails)principal).getAuthorities();
        } else {
            GlobalData.user_email = principal.toString();
        }
    }
}
