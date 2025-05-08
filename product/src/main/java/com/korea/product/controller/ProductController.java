package com.korea.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.dto.ProductDTO;
import com.korea.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 추가 기능을 만들기 -> @PostMapping을 갖는 메서드 만들기
	// 상품 추가
	// 사용자가 인터페이스(리엑트)에서 데이터가 넘어오겠다. -> 매게변수로 받기
	// JSON으로 넘어오는걸 자바 객체로 받기 -> @RequestBody
	// 사용자로부터 데이터 받을 때는 DTO를 써야한다.
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
		// 매개변수로 넘어온 데이터(productDTO)를 service로 넘기기
		// 추가를 하고 전체 조회를 하니깐 ProductDTO 타입의 리스트에 담아야겠다.
		List<ProductDTO> createdProduct = productService.addProduct(productDTO);
		return ResponseEntity.ok(createdProduct);
	}

	// 모든 상품 조회
	// 클라이언트가 최소금액을 전달할 수 있다.
	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestParam(value = "minPrice", required = false) Double minPrice) {
		List<ProductDTO> products = productService.getFilteredProducts(minPrice);
		return ResponseEntity.ok(products);
	}

	 // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }
	// 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
