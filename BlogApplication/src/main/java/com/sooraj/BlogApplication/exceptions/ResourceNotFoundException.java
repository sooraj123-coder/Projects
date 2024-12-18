package com.sooraj.BlogApplication.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String resourceFieldName;
    Integer fieldVal;

    public ResourceNotFoundException(String resourceName,String fieldName,Integer fieldValue){
        super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue));
        //super("Resource Not Found");
        this.fieldVal=fieldValue;
        this.resourceName=resourceName;
        this.resourceFieldName=fieldName;
    }
}
