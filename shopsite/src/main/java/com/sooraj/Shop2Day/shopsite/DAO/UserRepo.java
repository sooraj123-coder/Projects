package com.sooraj.Shop2Day.shopsite.DAO;


import com.sooraj.Shop2Day.shopsite.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    //nothing have to do here
    public User findUserByEmail(String emailId);
}
