package com.gmail.yurii.ecommerce.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "brand", "wineTitle", "color", "price"})
@Table(name = "wine")
public class Wine {
    /**
     * The unique code of the object.
     * The @Id annotation says that the field is the key for the current object.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * wine title.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of wine title field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    @Column(name = "wine_Title")
    private String wineTitle;

    /**
     * Wine manufacturer.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of wine manufacturer field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String brand;

    /**
     * The year the wine was released.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotNull(message = "Заповніть поле")
    private Integer year;

    /**
     * Manufacturer country.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String country;

    /**
     * color.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String color;

    /**
     * grape.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String grape;

    /**
     * combination.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String combination;

    /**
     * decantation.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String decantation;

    /**
     * Wine description.
     */
    private String description;

    /**
     * Wine image.
     */
    private String filename;

    /**
     * Wine price.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotNull(message = "Заповніть поле")
    private Integer price;

    /**
     * Wine volume.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String volume;

    /**
     * Type of fragrance.
     * The @NotBlank annotation says the field should not be empty.
     * Max length of manufacturer country field is 255 characters.
     */
    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String type;
}