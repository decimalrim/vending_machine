package vending_machine;


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

		
//		printTemperatureProduct(p); // is a 관계가 형성이 안되기 때문에 -> 다형성
		
		
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
		// VendingMachine is a Seller
		// RefundableVendingMachine is a Seller
		// Seller drinkVendingMachine = new VendingMachine();
		// Seller drinkVendingMachine = new RefundableVendingMachine();
		
		
	// 객체지향(=캡슐화: 기능1개에서 여러 처리를 하는 특징) 방식으로 개발 (행동기준) - 이게 더 나은 방식
		
		Seller drinkMachine = new VendingMachine();
		//아래 코드는 vendingMachine 생성자에서 대체함

		
		
		Customer musk = new Customer(200_000);// 어딘가에 있으면 파라미터가 없는 생성자는 쓸 수 없음

		
		drinkMachine.insertMoney(musk, "제로펩시");
		drinkMachine.pressButton(musk, "제로펩시", 50);// 파라미터에 누가 돈을 넣고 버튼을 누르는지 작성

		drinkMachine.insertMoney(musk, "제로콜라");
		drinkMachine.pressButton(musk, "제로콜라");//2개 샀을 때 확인 할 수 있음 
		
		drinkMachine.printProducts();
		musk.printProducts();
		
		
		
		Seller snackMachine = new RefundableVendingMachine(400);
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 50);
		
		snackMachine.insertMoney(musk, "제로펩시");
		snackMachine.pressButton(musk, "제로펩시", 2);
		
		snackMachine.printProducts();
		musk.printProducts();
		
	}



}
