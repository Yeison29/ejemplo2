package com.ejemplo2.ejemplo2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 300)
    private String description;
    @Column(length = 100,nullable = false)
    private String categori;
    @Column(length = 50,nullable = false)
    private int value;
}
