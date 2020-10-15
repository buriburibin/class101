package net.class101.homework1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.class101.homework1.Exception.SoldOutException;
import net.class101.homework1.service.OrderService;
import net.class101.homework1.vo.ProductVO;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	public List<ProductVO> getAllProducts() {
		List<ProductVO> products = orderService.getAllProducts();
		return products;
	}
	
	public ProductVO getProduct(int id) {
		ProductVO product = orderService.getProduct(id);
		return product;
	}
	
	public List<ProductVO> updateProduct(List<ProductVO> orderList) throws SoldOutException {
		List<ProductVO> products = new ArrayList<ProductVO>();
		for (ProductVO order : orderList) {
			
			
			ProductVO product = orderService.updateById(order.getId(), order);
			
			products.add(product);
		}
		
		return products;
	}
}
