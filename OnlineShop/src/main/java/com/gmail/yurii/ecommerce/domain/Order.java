package com.gmail.yurii.ecommerce.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "user", "wineList"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_price")
    private Double totalPrice;

    private LocalDate date;

    @NotBlank(message = "Заповніть поле")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Заповніть поле")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Заповніть поле")
    private String city;

    @NotBlank(message = "Заповніть поле")
    private String address;

    @Email(message = "Некорректний email")
    @NotBlank(message = "Заповніть поле")
    private String email;

    @NotBlank(message = "Номер телефона не может бути пустим")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Поштовий індекс не может бути пустим")
    @Min(value = 5, message = "Поштовий індекс повинен містити 5 цифр")
    @Column(name = "post_index")
    private Integer postIndex;

    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Wine> wineList;

    @ManyToOne
    private User user;

    public Order(User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.wineList = new ArrayList<>();
    }
}
