package com.example.mvccrud.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    @NotBlank(message="required field !")
    private String firstName;
    @NotBlank(message="required field !")
    private String lastName;
    @NotBlank(message="required field !")
    @Email(message="this field must be of email format !")
    private String email;
}
