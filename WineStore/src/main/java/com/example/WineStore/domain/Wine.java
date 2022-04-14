package com.example.WineStore.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Wine {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(max = 255)
    @Column(name = "wine_title")
    private String wineTitle;


    @NotBlank
    @Length(max = 255)
    private String type;

    @NotBlank
    @Length(max = 255)
    private String volume;

    private String brand;

    private String strength;

    private String country;

    private String color;

    private String temp;

    private String grape;

    private String description;

    private String filename;

    @NotNull
    private Integer price;




}