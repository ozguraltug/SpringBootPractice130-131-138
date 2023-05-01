package com.tpe.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
    @Column(unique = true,nullable=false)
    private String email;


    private String Phone;

    @OneToMany(mappedBy="customer")
    private List<Product> products=new ArrayList<>();

}
