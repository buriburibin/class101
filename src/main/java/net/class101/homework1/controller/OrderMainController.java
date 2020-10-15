package net.class101.homework1.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.class101.homework1.vo.ProductVO;

@SpringBootApplication
public class OrderMainController implements CommandLineRunner  {

	@Autowired
	OrderController orderController;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderMainController.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);	
		DecimalFormat df = new DecimalFormat("###,###");
		
		while (true) {
			System.out.println();
			System.out.print("입력(o[order]: 주문, q[quit]: 종료):");
			
			String orderType = scanner.nextLine();
			
			if(!(orderType.toLowerCase().equals("o") || orderType.toLowerCase().equals("q"))) {
				System.out.println("정확한 명령어를 입력해주십시오.");
				continue;
			}
			if(orderType.toLowerCase().equals("q")) {
				System.out.println("주문을 종료합니다.");
				break;
			}
			
			List<ProductVO> productList =  orderController.getAllProducts();
			
			System.out.println();
			System.out.println("상품번호\t\t\t\t상품명\t\t\t\t판매가격\t재고수");
			
			for (ProductVO product : productList) {
				String line = product.getId() + "\t" + product.getName();
				if(product.getName().getBytes().length < 38) {
					line += "\t\t\t\t\t";
				} else if(product.getName().getBytes().length < 50) {
					line += "\t\t\t\t";
				} else if(product.getName().getBytes().length < 60) {
					line += "\t\t\t";
				} else if(product.getName().getBytes().length < 70) {
					line += "\t\t";
				} else if(product.getName().getBytes().length < 90) {
					line += "\t";
				}
				line += product.getPrice() + "\t" + product.getQuantity();
				System.out.print(line);
				System.out.println("\t\t\t\t" + product.getName().getBytes().length);
			}
			
			List<ProductVO> orderList = new ArrayList<ProductVO>();
			
			while (true) {
				System.out.println();
				System.out.print("상품번호 : ");
				
				String productNum = scanner.nextLine();
				
				if(productNum.trim().equals("")) {
					break;
				}
				
				ProductVO order = null;
				try {
					int productId = Integer.parseInt(productNum);
					
					order = orderController.getProduct(productId);
					
					if (order == null) {
						System.out.println("정확하지 않은 상품번호를 입력하셨습니다.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("정확하지 않은 상품번호를 입력하셨습니다.");
					continue;
				}
				
				boolean quantityFlag = true;
				int quantity = 0;
				do {
					System.out.print("주문수량 : ");
					
					String orderQuantity = scanner.nextLine();
					
					if(orderQuantity.trim().equals("")) {
						break;
					}
					
					try {
						quantity = Integer.parseInt(orderQuantity);
					} catch (Exception e) {
						System.out.println("수량은 숫자로 입력해주십시오.");
						continue;
					}
					quantityFlag = false;
				} while (quantityFlag);
				
				order.setQuantity(quantity);
				
				orderList.add(order);
				
			}
			
			if (orderList.size() < 1) {
				System.out.println("주문하신 내역이 없습니다.");
			} else {
				int allPrice = 0;
				
				System.out.println("주문 내역 : ");
				System.out.println("----------------------------------------------------");
				for (ProductVO order : orderList) {
					System.out.println(order.getName() + "\t" + order.getQuantity() + "개");
					allPrice += order.getPrice() * order.getQuantity();
				}
				System.out.println("----------------------------------------------------");
				System.out.println("주문금액: " + df.format(allPrice) + "원");
				
				if(allPrice < 50000) {
					System.out.println("배송비: " + df.format(5000) + "원");
					allPrice += 5000;
				}
				System.out.println("----------------------------------------------------");
				System.out.println("지불금액: " + df.format(allPrice) + "원");
				System.out.println("----------------------------------------------------");
				
			}
			orderController.updateProduct(orderList);
		}
		System.out.println("고객님의 주문에 감사드립니다.");
	}

}
