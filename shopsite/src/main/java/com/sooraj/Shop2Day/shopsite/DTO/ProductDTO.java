package com.sooraj.Shop2Day.shopsite.DTO;

import com.sooraj.Shop2Day.shopsite.Entity.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductDTO {
    private Long pid;
    private String pname;
    private double pprice;
    private double pweight;
    private String pdescription;
    private String pimagename;
    private int categoryId;
}
