package com.sooraj.Shop2Day.shopsite.Controller;

import com.sooraj.Shop2Day.shopsite.DAO.CategoryDAO;
import com.sooraj.Shop2Day.shopsite.DAO.ProductRepo;
import com.sooraj.Shop2Day.shopsite.DTO.ProductDTO;
import com.sooraj.Shop2Day.shopsite.Entity.Category;
import com.sooraj.Shop2Day.shopsite.Service.CategoryService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    CategoryService theCategoryService;

    @Autowired
    ProductService theProductService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }


    //Categories

    @GetMapping("/admin/categories")
    public String showCategories(Model theModel){
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getaddCategory(Model theModel){
        theModel.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping ("/admin/categories/add")
    public String postaddCategory(@ModelAttribute("category") Category theCategory){
        theCategoryService.addCategory(theCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        theCategoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    //update Category
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Integer id, Model theMdel){
        Optional<Category> data = theCategoryService.findCategoryById(id);
        if(data.isPresent()){
            data.get().toString();
            theMdel.addAttribute("category",data.get());
            return "categoriesAdd";
        }else{
            return "404";
        }
    }
    //Products

    @GetMapping("/admin/products")
    public String showProoducts(Model theModel){
        theModel.addAttribute("products", theProductService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model theModel){
        theModel.addAttribute("productDTO",new ProductDTO());
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        return "productsAdd";
    }
}
