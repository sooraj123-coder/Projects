package com.sooraj.Shop2Day.shopsite.DAO;

import com.sooraj.Shop2Day.shopsite.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    //nothing to do
    public List<Cart> findByCartEmail(String emailparam);
}
