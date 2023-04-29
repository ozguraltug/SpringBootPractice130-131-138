package com.tpe.dto;

import com.tpe.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {


    @NotNull(message ="First name can not be null")//null olamaz ama boşluk olur
    @NotBlank(message="First name can not be blank") //null olamaz boşluk olamaz
    @NotEmpty(message="First name can not be empty")
    @Size(min=2,max=50)
    private String name;

    @NotNull(message ="Last name can not be null")
    @NotBlank(message="Last name can not be blank")
    @Size(min=2,max=50)
    private String lastName;


    @Email //...@..
    private String email;


    private String Phone;
    public CustomerDTO(Customer customer){
        this.name=customer.getName();
        this.lastName=customer.getLastName();
        this.email=customer.getEmail();
        this.Phone=customer.getPhone();
    }

}
