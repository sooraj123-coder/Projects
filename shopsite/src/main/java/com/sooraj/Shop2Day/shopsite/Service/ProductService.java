package com.sooraj.Shop2Day.shopsite.Service;

import com.sooraj.Shop2Day.shopsite.DAO.CategoryDAO;
import com.sooraj.Shop2Day.shopsite.DAO.ProductRepo;
import com.sooraj.Shop2Day.shopsite.DTO.ProductDTO;
import com.sooraj.Shop2Day.shopsite.Entity.Category;
import com.sooraj.Shop2Day.shopsite.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo theProductRepo;

    public List<Product> getAllProducts(){
        return theProductRepo.findAll();
    }

   public void addProduct(Product theProduct){
       theProductRepo.save(theProduct);
    }

    public void removeProductById(Long id){
        theProductRepo.deleteById(id);
    }

    public Optional<Product> getProductById(Long id){
        return theProductRepo.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(Integer id){
        return theProductRepo.findAllByCategory_Id(id);
    }
}
