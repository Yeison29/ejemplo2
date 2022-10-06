package com.ejemplo2.ejemplo2.repository;

import com.ejemplo2.ejemplo2.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    List<Products> findAllByCategori(String categori);
    List<Products> findAllByCategoriAndName(String categori, String name);
}
