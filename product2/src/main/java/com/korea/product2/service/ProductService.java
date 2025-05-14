package com.korea.product2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> create(ProductDTO dto) {
        ProductEntity productEntity = ProductDTO.toEntity(dto);
        productEntity = productRepository.save(productEntity); // 날짜 자동 처리됨
        return List.of(new ProductDTO(productEntity)); // 저장된 엔티티로 DTO 변환 후 반환
    }
    
    public ProductEntity findById(int id) {
        return productRepository.findById(id).orElse(null);
    }
    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }
}
