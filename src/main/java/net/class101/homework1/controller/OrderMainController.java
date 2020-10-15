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
			System.out.print("�Է�(o[order]: �ֹ�, q[quit]: ����):");
			
			String orderType = scanner.nextLine();
			
			if(!(orderType.toLowerCase().equals("o") || orderType.toLowerCase().equals("q"))) {
				System.out.println("��Ȯ�� ��ɾ �Է����ֽʽÿ�.");
				continue;
			}
			if(orderType.toLowerCase().equals("q")) {
				System.out.println("�ֹ��� �����մϴ�.");
				break;
			}
			
			List<ProductVO> productList =  orderController.getAllProducts();
			
			System.out.println();
			System.out.println("��ǰ��ȣ\t\t\t\t��ǰ��\t\t\t\t�ǸŰ���\t����");
			
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
				System.out.print("��ǰ��ȣ : ");
				
				String productNum = scanner.nextLine();
				
				if(productNum.trim().equals("")) {
					break;
				}
				
				ProductVO order = null;
				try {
					int productId = Integer.parseInt(productNum);
					
					order = orderController.getProduct(productId);
					
					if (order == null) {
						System.out.println("��Ȯ���� ���� ��ǰ��ȣ�� �Է��ϼ̽��ϴ�.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("��Ȯ���� ���� ��ǰ��ȣ�� �Է��ϼ̽��ϴ�.");
					continue;
				}
				
				boolean quantityFlag = true;
				int quantity = 0;
				do {
					System.out.print("�ֹ����� : ");
					
					String orderQuantity = scanner.nextLine();
					
					if(orderQuantity.trim().equals("")) {
						break;
					}
					
					try {
						quantity = Integer.parseInt(orderQuantity);
					} catch (Exception e) {
						System.out.println("������ ���ڷ� �Է����ֽʽÿ�.");
						continue;
					}
					quantityFlag = false;
				} while (quantityFlag);
				
				order.setQuantity(quantity);
				
				orderList.add(order);
				
			}
			
			if (orderList.size() < 1) {
				System.out.println("�ֹ��Ͻ� ������ �����ϴ�.");
			} else {
				int allPrice = 0;
				
				System.out.println("�ֹ� ���� : ");
				System.out.println("----------------------------------------------------");
				for (ProductVO order : orderList) {
					System.out.println(order.getName() + "\t" + order.getQuantity() + "��");
					allPrice += order.getPrice() * order.getQuantity();
				}
				System.out.println("----------------------------------------------------");
				System.out.println("�ֹ��ݾ�: " + df.format(allPrice) + "��");
				
				if(allPrice < 50000) {
					System.out.println("��ۺ�: " + df.format(5000) + "��");
					allPrice += 5000;
				}
				System.out.println("----------------------------------------------------");
				System.out.println("���ұݾ�: " + df.format(allPrice) + "��");
				System.out.println("----------------------------------------------------");
				
			}
			orderController.updateProduct(orderList);
		}
		System.out.println("������ �ֹ��� ����帳�ϴ�.");
	}

}
