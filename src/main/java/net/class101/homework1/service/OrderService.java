package net.class101.homework1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.class101.homework1.Exception.SoldOutException;
import net.class101.homework1.dao.OrderRepository;
import net.class101.homework1.vo.ProductVO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<ProductVO> getAllProducts() {
		List<ProductVO> products = new ArrayList<>();
		orderRepository.findAll().forEach(e -> products.add(e));
		return products;
	}
	
	public ProductVO getProduct(int id) {
		Optional<ProductVO> e = orderRepository.findById(id);
		
		ProductVO product = null;
		if (e.isPresent()) {
			product = e.get();
		}
		return product;
	}

	public ProductVO updateById(int id, ProductVO order) throws SoldOutException {
		Optional<ProductVO> e = orderRepository.findById(id);
		
		ProductVO product = e.get();
		
		if(product.getQuantity() - order.getQuantity() < 0) {
			throw new SoldOutException(product.getName() + " 상품의 재고가 부족합니다.");
		} else {
			product.setQuantity(product.getQuantity() - order.getQuantity());
		}
		
		orderRepository.save(product);
		
		return product;
	}
}
