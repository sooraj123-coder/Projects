package com.sooraj.Shop2Day.shopsite.DAO;

import com.sooraj.Shop2Day.shopsite.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
    //nothing have to do
}
