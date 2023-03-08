package com.example.mvccrud.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    @Size(min=2,max=15,message = "field size is not correct")
    @NotBlank(message="required field !")
    private String firstName;
    @Size(min=2,max=15,message = "field size is not correct")
    @NotBlank(message="required field !")
    private String lastName;
    @NotBlank(message="required field !")
    @Email(message="this field must be of email format !")
    private String email;
}
