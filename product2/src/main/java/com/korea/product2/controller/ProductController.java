package com.korea.product2.controller;  

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.dto.ResponseDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController { 

    @Autowired
    private ProductService service;

    @GetMapping("/Products")
    public ResponseEntity<?> getAllProduct() {
        List<ProductDTO> dtos = service.findAll();  // 이미 DTO 리스트를 가져옴

        ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/Products") 
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto) {
        List<ProductDTO> createproduct = service.create(dto);
        return ResponseEntity.ok(createproduct);
    }
    
    
    
    @PutMapping("/Products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto) {
        ProductEntity entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        entity.setP_name(dto.getP_name());
        entity.setP_price(dto.getP_price());
        entity.setP_count(dto.getP_count());

        ProductEntity updated = service.save(entity);
        return ResponseEntity.ok(new ProductDTO(updated));
    }

}
