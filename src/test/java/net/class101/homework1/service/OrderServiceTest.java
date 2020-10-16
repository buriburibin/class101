package net.class101.homework1.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.class101.homework1.Exception.SoldOutException;
import net.class101.homework1.vo.ProductVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void getProductTest() {
		ProductVO product = new ProductVO();
		product.setId(53144);
		product.setType("KLASS");
		product.setName("���� �����, �޸� ���� ������. �ɵ��Ʈ�� ����, �׸���");
		product.setPrice(249500);
		product.setQuantity(99999);
		
		ProductVO product1 = orderService.getProduct(product.getId());
		
		assertEquals(product.getName(), product1.getName());
	}
	
	@Test
	public void updateProductSOE() throws SoldOutException {
		ProductVO order = new ProductVO();
		order.setId(97166);
		order.setType("KIT");
		order.setName("�̷��� ���� ��äȭ ŰƮ,���? Ŭ����101�� �����ȭ���� ��ȹ�� 3���� ��äȭ ŰƮ");
		order.setPrice(96900);
		order.setQuantity(3);
	
		while (true) {
			ProductVO product = orderService.updateById(97166, order);
			assertEquals(order.getName(), product.getName());
		}
	}
	
	@Test
	public void updateProduct() throws SoldOutException {
		ProductVO order = new ProductVO();
		order.setId(97166);
		order.setType("KIT");
		order.setName("�̷��� ���� ��äȭ ŰƮ,���? Ŭ����101�� �����ȭ���� ��ȹ�� 3���� ��äȭ ŰƮ");
		order.setPrice(96900);
		order.setQuantity(3);
		
		ProductVO product = orderService.updateById(97166, order);
		assertEquals(order.getName(), product.getName());
	}
}
