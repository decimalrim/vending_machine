package vending_machine;

import java.util.ArrayList;
import java.util.List;

import vending_machine.util.FileUtil;

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
	
	/*
	 * Refactoring ==> 코드를 깔끔하게 개선하는 과정
	 * 1. 메소드 Body 라인 수 : 20라인 이하 작성할 것
	 * 2. 클래스명, 메소드명, 변수(인스턴스)명은 명확하게(축약, 애매모호x) 지을 것
	 * 3. 메소드 구성은 신문기사처럼 쓸 것
	 *     - 기사의 내용처럼, 편하게 읽을 수 있도록 만든다
	 *      - 메소드 Chain을 순서대로 작성
	 *       - 주문() -> 재고수 감소() -> 돈을 증가() -> 고객에게 재고를 증가()시킨다
	 *       - 주문()
	 *       - 재고수 감소()
	 *       - 돈을 증가()
	 *       - 고객에게 재고를 증가()
	 */
	
	public static List<Product> initiateProduct() {
		
		List<Product> productList = FileUtil.readCSVFile("C:\\Java Exam", "goods.csv");	
		return productList;
	}
	
	public static void initiateInsertMoneyHandler(Sellable<Product> sellable) {
		
		sellable.setInsertMoneyHandler(new InsertMoneyHandler<Product>() {

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
		
	}
	
	
	public static void initiatePressButtonHandler(Sellable<Product> sellable) {
		
		sellable.setPressButtonHandler(new PressButtonHandler<Product>() {

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
	}
	
	
	public static void initiatePrintHandler(Sellable<Product> sellable) {
		
		sellable.setPrintHandler(new PrintHandler<Product>() {

			@Override
			public void handle(Product item) {
					System.out.println("자판기의 상품 수량: " + item.getQuantity());
					System.out.println("자판기의 상품 이름: " + item.getName());
				}			
		});
	}
	
	
	
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
		
		
//		Product p = new Product();
//		p.setName("보드마카");
//		p.setPrice(500);
//		p.setQuantity(40);
//		
//		System.out.println(p); 
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
		
		
		
	// 객체지향(=캡슐화: 기능1개에서 여러 처리를 하는 특징) 방식으로 개발 (행동기준) - 이게 더 나은 방식
		
		Sellable<Product> drinkMachine = new VendingMachine<>(100_000, initiateProduct());
		initiateInsertMoneyHandler(drinkMachine);
		initiatePressButtonHandler(drinkMachine);
		initiatePrintHandler(drinkMachine);
		
		drinkMachine.addProduct("보이차", 4000, 20);
				

		Customer musk = new Customer(200_000);// 어딘가에 있으면 파라미터가 없는 생성자는 쓸 수 없음

		
		drinkMachine.insertMoney(musk, "제로펩시");
		drinkMachine.pressButton(musk, "제로펩시", 50);// 파라미터에 누가 돈을 넣고 버튼을 누르는지 작성

		drinkMachine.insertMoney(musk, "제로콜라");
		drinkMachine.pressButton(musk, "제로콜라");//2개 샀을 때 확인 할 수 있음 
		
		drinkMachine.printProducts();
		musk.printProducts();
		
		
		
		Sellable<Product> snackMachine = new RefundableVendingMachine<>(400, initiateProduct());
		initiateInsertMoneyHandler(snackMachine);
		initiatePressButtonHandler(snackMachine);
		initiatePrintHandler(snackMachine);
		
		drinkMachine.addProduct("엔쵸", 700, 10);
		
		
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 50);
		
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 2);
		
		snackMachine.printProducts();
		musk.printProducts();
		
	}



}
