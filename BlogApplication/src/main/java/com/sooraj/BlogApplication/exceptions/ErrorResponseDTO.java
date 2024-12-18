package com.sooraj.BlogApplication.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ErrorResponseDTO {
   private String exceptionName;
   private String exceptionMessage;

   public ErrorResponseDTO(String name, String message){
      this.exceptionName=name;
      this.exceptionMessage=message;
   }
}
