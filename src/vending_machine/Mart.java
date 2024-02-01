package vending_machine;

import java.util.ArrayList;
import java.util.List;

public class Mart {
	

//	
//	public static void printProduct(Product p) {
//		System.out.println(p.getName()); // null
//		
//		if ( p instanceof TemperatureProduct) {
//			TemperatureProduct tp = (TemperatureProduct) p; // 형변환
//			tp.setIsHot();
//			System.out.println(tp.getIsHot());
//			
//		}
//	}
//	
	
		
	
	public static void main(String[] args) {
		// 모든 클래스의 슈퍼클래스는  Object
		
		/*
		 * object
		 * --> Product
		 *  ---> TemperatureProduct
		 * Product is a Object
		 * TemperatureProduct is a Product
		 * TemperatureProduct is a Object  
		 */
//		Product p = new Product();
//		TemperatureProduct tp = new TemperatureProduct();
//		
//		tp.setName("티비");
//		printProduct(p);
//		printProduct(tp);

		
//		printTemperatureProduct(tp); // is a 관계가 형성이 안되기 때문에 -> 다형성
		
		
		Product p = new Product();
		p.setName("보드마카");
		p.setPrice(500);
		p.setQuantity(40);
		
		System.out.println(p); 
		// Product에서 toString을 해주었기 때문에 해시데이터(래퍼런스타입)가 아닌 결과 값으로 나옴
		// vending_machine.Product@23c3023d
//		   제품명 :보드마카, 가격 :500, 재고 :40	
		
		
		// Seller
		// --> VendingMachine
		// --> RefundableVendingMachine
		// IS A
		// VendingMachine is a Sellable
		// RefundableVendingMachine is a Sellable
		// Sellable drinkVendingMachine = new VendingMachine();
		// Sellable drinkVendingMachine = new RefundableVendingMachine();
		
		
		//  Sellable (인터페이스)
		// --> (구현) VendingMachine
		// --> (구현) RefundVendingMachine
		
		
		List<Product> productList = new ArrayList<>();
		
		Product product1 = new Product();
		product1.setName("제로콜라");
		product1.setPrice(1600);
		product1.setQuantity(50);
		productList.add(product1);
		
		Product product2 = new Product();
		product2.setName("제로펩시");
		product2.setPrice(1500);
		product2.setQuantity(30);
		productList.add(product2);
		
		Product product3 = new Product();
		product3.setName("제로스프라이트");
		product3.setPrice(1400);
		product3.setQuantity(20);
		productList.add(product3);
		
	// 객체지향(=캡슐화: 기능1개에서 여러 처리를 하는 특징) 방식으로 개발 (행동기준) - 이게 더 나은 방식
		
		Sellable<Product> drinkMachine = new VendingMachine<>(100_000, productList);
		drinkMachine.setInsertMoneyHandler(new InsertMoneyHandler<Product>() {

			@Override // 익명클래스
			public void handle(VendingMachine<Product> machine, Customer customer,
						        Product item, String productName) {
				if (item.equals(productName)){
					int money = machine.getMoney();
					money += item.getPrice();
					machine.setMoney(money);
					
					customer.pay(item.getPrice());
				}
			}});
		
		
		drinkMachine.setPressButtonHandler(new PressButtonHandler<Product>() {

			@Override
			public void handle(VendingMachine<Product> machine, Customer customer,
								Product item, String productName,int orderCount) {
				if (item.equals(productName)){ // product에서 overriding 했을때
					
					if(item.getQuantity() <= 0) {
						machine.refund(customer, item.getPrice());
						return; // 메소드를 종료
					}
					
					int quantity = item.getQuantity();
					quantity -= orderCount;
					item.setQuantity(quantity);
					
					customer.addStock(productName, item.getPrice(), orderCount);
	
				}
				
			}});
		
		drinkMachine.setPrintHandler(new PrintHandler<Product>() {

			@Override
			public void handle(Product item) {
					System.out.println("자판기의 상품 수량: " + item.getQuantity());
					System.out.println("자판기의 상품 이름: " + item.getName());
				}			
		});

		Customer musk = new Customer(200_000);// 어딘가에 있으면 파라미터가 없는 생성자는 쓸 수 없음

		
		drinkMachine.insertMoney(musk, "제로펩시");
		drinkMachine.pressButton(musk, "제로펩시", 50);// 파라미터에 누가 돈을 넣고 버튼을 누르는지 작성

		drinkMachine.insertMoney(musk, "제로콜라");
		drinkMachine.pressButton(musk, "제로콜라");//2개 샀을 때 확인 할 수 있음 
		
		drinkMachine.printProducts();
		musk.printProducts();
		
		
		
		Sellable<Product> snackMachine = new RefundableVendingMachine<>(400, productList);
		snackMachine.setInsertMoneyHandler(new InsertMoneyHandler<Product>() {

			@Override // 익명클래스
			public void handle(VendingMachine<Product> machine, Customer customer,
						        Product item, String productName) {
				if (item.equals(productName)){
					int money = machine.getMoney();
					money += item.getPrice();
					machine.setMoney(money);
					
					customer.pay(item.getPrice());
				}
			}});
		
		
		snackMachine.setPressButtonHandler(new PressButtonHandler<Product>() {

			@Override
			public void handle(VendingMachine<Product> machine, Customer customer,
								Product item, String productName,int orderCount) {
				if (item.equals(productName)){ 
					
					if(item.getQuantity() <= 0) {
						machine.refund(customer, item.getPrice());
						return; // 메소드를 종료
					}
					
					int quantity = item.getQuantity();
					quantity -= orderCount;
					item.setQuantity(quantity);
					
					customer.addStock(productName, item.getPrice(), orderCount);
	
				}
				
			}});
		
		snackMachine.setPrintHandler(new PrintHandler<Product>() {

			@Override
			public void handle(Product item) {
					System.out.println("자판기의 상품 수량: " + item.getQuantity());
					System.out.println("자판기의 상품 이름: " + item.getName());
				}			
		});
		
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 50);
		
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 2);
		
		snackMachine.printProducts();
		musk.printProducts();
		
	}



}
