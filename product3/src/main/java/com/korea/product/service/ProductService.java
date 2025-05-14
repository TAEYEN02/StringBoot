package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 상품 추가
    //넘어온 데이터를 db에 추가하고 전체를 조회해서 변환을 해야하니깐
    //반환형을 List<ProductDTO>로 해야겠다
    public List<ProductDTO> addProduct(ProductDTO productDTO) {
    	//jpa 메서드를 이용해서 데이터베이스에 추가
    	//save(저장해야하니) -> save에 들어갈 수 있는 타입이 Entity
    	//넘어온 DTO를 Entity로 바꾸기 
        ProductEntity entity = ProductDTO.toEntity(productDTO);
        productRepository.save(entity);
        //전체 조회한 값을 반환하려면 findAll()을 써야한다
        //findAll()의 반환형이 List<ProductEntity>이니깐 List<ProductDTO>로 바꾸기
        return productRepository
        		.findAll()
        		.stream()
        		.map(ProductDTO::new)
        		.collect(Collectors.toList());
    }
    
    public List<ProductDTO> getAllProuct(){
    	return productRepository.findAll().stream()
    			.map(ProductDTO::new)
    			.collect(Collectors.toList());
    }

    // 필터링된 상품 조회
    public List<ProductDTO> getFilteredProducts(Double minPrice) {
        List<ProductEntity> products = productRepository.findAll();

        // 가격 필터링 (minPrice가 있을 경우)
        if (minPrice != null) {
            products = products.stream()
                    .filter(product -> product.getPrice() >= minPrice)
                    .collect(Collectors.toList());
        }
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
    
    // 상품 수정
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Optional<ProductEntity> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ProductEntity entity = optionalEntity.get();
            entity.setName(productDTO.getName());
            entity.setDescription(productDTO.getDescription());
            entity.setPrice(productDTO.getPrice());
            productRepository.save(entity);
            return new ProductDTO(entity);
        }
        return null;
    }
    
    
    
    // 상품 삭제
    public boolean deleteProduct(int id) {
        Optional<ProductEntity> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}