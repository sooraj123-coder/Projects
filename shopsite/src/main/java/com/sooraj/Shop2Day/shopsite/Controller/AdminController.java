package com.sooraj.Shop2Day.shopsite.Controller;

import com.sooraj.Shop2Day.shopsite.DAO.CategoryDAO;
import com.sooraj.Shop2Day.shopsite.DAO.ProductRepo;
import com.sooraj.Shop2Day.shopsite.DTO.ProductDTO;
import com.sooraj.Shop2Day.shopsite.Entity.Category;
import com.sooraj.Shop2Day.shopsite.Entity.Product;
import com.sooraj.Shop2Day.shopsite.Service.CategoryService;
import com.sooraj.Shop2Day.shopsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/images/productImages";
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

    @PostMapping("/admin/products/add")
    public String  addProduct(@ModelAttribute("productDTO") ProductDTO theProductDTO, @RequestParam("productImage")MultipartFile themultipartfile, @RequestParam("imgName") String imgname)throws IOException {

        Product theProduct= new Product();

        theProduct.setPid(theProductDTO.getPid());
        theProduct.setPname(theProductDTO.getPname());
        theProduct.setPprice(theProductDTO.getPprice());
        theProduct.setPweight(theProductDTO.getPweight());
        theProduct.setPdescription(theProductDTO.getPdescription());
        theProduct.setPimagename(theProductDTO.getPimagename());
        theProduct.setCategory(theCategoryService.findCategoryById(theProductDTO.getCategoryId()).get());

        String Imageuuid;
        if(!themultipartfile.isEmpty()){
            Imageuuid=themultipartfile.getOriginalFilename();
            Path filenameandpath= Paths.get(uploadDir,Imageuuid);
            Files.write(filenameandpath,themultipartfile.getBytes());

        }else{
            Imageuuid=imgname;
        }
        theProduct.setPimagename(Imageuuid);

        theProductService.addProduct(theProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deletePoduct(@PathVariable Long id){
        theProductService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProuct(@PathVariable Long id, Model theModel ){

        Product theProduct = theProductService.getProductById(id).get();
        ProductDTO theProductDTO=new ProductDTO();

        theProductDTO.setPid(theProduct.getPid());
        theProductDTO.setPname(theProduct.getPname());
        theProductDTO.setCategoryId(theProduct.getCategory().getId());
        theProductDTO.setPprice(theProduct.getPprice());
        theProductDTO.setPweight(theProduct.getPweight());
        theProductDTO.setPdescription(theProduct.getPdescription());
        theProductDTO.setPimagename(theProduct.getPimagename());
        theModel.addAttribute("productDTO",theProductDTO);
        theModel.addAttribute("categories",theCategoryService.getAllCategory());
        return "productsAdd";
    }
}
