package com.sooraj.Shop2Day.shopsite.DAO;

import com.sooraj.Shop2Day.shopsite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
    //nothing have to do
}
