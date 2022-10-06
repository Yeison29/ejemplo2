package com.ejemplo2.ejemplo2.controllers;

import com.ejemplo2.ejemplo2.models.Products;
import com.ejemplo2.ejemplo2.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;
    @PostMapping(value = "/product")
    public ResponseEntity createProduct(@RequestBody Products products){
        try{
            productsRepository.save(products);
            return new ResponseEntity(products, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "/products")
    public ResponseEntity listProducts(){
        List<Products> products = productsRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(products,HttpStatus.OK);
        }
    }
    @GetMapping(value = "/products/{categori}")
    public ResponseEntity listCategoriProducts(@PathVariable String categori){
        List<Products> products = productsRepository.findAllByCategori(categori);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(products,HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity editProduct(@PathVariable Long id, @RequestBody Products products){
        Optional<Products> productsDB = productsRepository.findById(id);
        if(productsDB.isPresent()){
            try{
                productsDB.get().setName(products.getName());
                productsDB.get().setDescription(products.getDescription());
                productsDB.get().setCategori(products.getCategori());
                productsDB.get().setValue(products.getValue());
                productsRepository.save(productsDB.get());
                return new ResponseEntity(productsDB,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Products> product = productsRepository.findById(id);
        if(product.isPresent()){
            productsRepository.delete(product.get());
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/products/{categori}/{name}")
    public ResponseEntity listCategoriaAndNombre(@PathVariable String categori,@PathVariable String name){
        List<Products> productsDB = productsRepository.findAllByCategoriAndName(categori,name);
        if(productsDB.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(productsDB,HttpStatus.OK);
        }
    }
}
