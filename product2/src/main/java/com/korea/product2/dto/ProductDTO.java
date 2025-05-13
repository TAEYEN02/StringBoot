package com.korea.product2.dto;

import java.time.LocalDateTime;

import org.springframework.web.service.annotation.GetExchange;

import com.korea.product2.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	private int p_id;
	private String p_name;
	private int p_count;
	private int p_price;
	private LocalDateTime p_create_date;
	private LocalDateTime p_update_date;
	
	public ProductDTO(ProductEntity entity) {
		this.p_id = entity.getP_id();
		this.p_name = entity.getP_name();
		this.p_count = entity.getP_count();
		this.p_price = entity.getP_price();
        this.p_create_date = entity.getP_create_date();
        this.p_update_date = entity.getP_update_date();
    }
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
							.p_id(dto.getP_id())
							.p_name(dto.getP_name())
							.p_count(dto.getP_count())
							.p_price(dto.getP_price())
							.p_create_date(dto.getP_create_date())
							.p_update_date(dto.getP_update_date())
							.build();
	}
}
