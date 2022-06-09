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
    /**
     * The unique code of the object.
     * The @Id annotation says that the field is the key for the current object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Order total price.
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * Date when the order was made.
     */
    private LocalDate date;

    /**
     * The first name of the customer who placed the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Заповніть поле")
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the customer who placed the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Заповніть поле")
    @Column(name = "last_name")
    private String lastName;

    /**
     * City of delivery of the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Заповніть поле")
    private String city;

    /**
     * Delivery address of the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Заповніть поле")
    private String address;

    /**
     * Customer email.
     * The @Email annotation says the string has to be a well-formed email address.
     * The @NotBlank annotation says the field should not be empty.
     */
    @Email(message = "Некорректний email")
    @NotBlank(message = "Заповніть поле")
    private String email;

    /**
     * Customer phone number.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Номер телефона не может бути пустим")
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Customer post index.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotNull(message = "Поштовий індекс не может бути пустим")
    @Min(value = 5, message = "Поштовий індекс повинен містити 5 цифр")
    @Column(name = "post_index")
    private Integer postIndex;

    /**
     * List of products in the order.
     * Between the {@link Order} and {@link Wine} objects, there is a many-to-many relationship, that is,
     * every record in one table is directly related to every record in another table.
     * Sampling on first access to the current object.
     */
    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Wine> wineList;

    /**
     * The customer who made the order.
     * Between the {@link Order} and {@link User} objects, there is a many-to-one relationship, that is,
     * each record in one table is directly related to a single record in another table.
     */
    @ManyToOne
    private User user;

    public Order(User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.wineList = new ArrayList<>();
    }
}
