package com.sooraj.Shop2Day.shopsite.Service;

import com.sooraj.Shop2Day.shopsite.DAO.UserRepo;
import com.sooraj.Shop2Day.shopsite.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo theUserRepo;

    @Transactional
    public void addUser(User theUser){
        theUserRepo.save(theUser);
    }

    public User getUserByEmail(String emailId){
        User user = theUserRepo.findUserByEmail(emailId);
        return user;
    }
}
