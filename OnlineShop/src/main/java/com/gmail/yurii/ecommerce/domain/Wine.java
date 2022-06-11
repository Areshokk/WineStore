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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    @Column(name = "wine_Title")
    private String wineTitle;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String brand;

    @NotNull(message = "Заповніть поле")
    private Integer year;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String country;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String color;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String grape;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String combination;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String decantation;

    private String description;

    private String filename;

    @NotNull(message = "Заповніть поле")
    private Integer price;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String volume;

    @NotBlank(message = "Заповніть поле")
    @Length(max = 255)
    private String type;
}