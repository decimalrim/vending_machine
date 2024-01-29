package vending_machine;

public class Customer {
	
	
	//멤버 변수
	/**
	 * 고객이 가진 돈
	 */
	private int wallet;
	
	/**
	 * 고객이 가진 상품의 수량
	 */
//	int stock;
	private Product[] productArray;
	
	// getter 만들기
	
	public int getWallet() {
		return this.wallet;
	}
	
	public Product[] getProduct() {
		return this.productArray;
	}
	
	// 생성자
//	public Customer(int wallet, int stock) {
	public Customer(int wallet) {
		// 생성자가 만든 인스턴스의 this//
		this.wallet = wallet; //이것들은 모두 파라미터이다 자기가 자신에게 같은 값 할당
//		this.stock = stock;
		this.productArray = new Product[3];	
		}
	
	/**
	 * 지출한다
	 */
	public void pay(int price) { //파라미터로 price값 받아옴
		if (this.wallet - price <= 0) {
			return; // 메소드 즉시 종료
		}
		this.wallet -= price;
	}
	
	/**
	 * 환불 받는디
	 * @param money 환불 받은 금액
	 */
	public void addMoney(int money) {
		this.wallet += money;
	}
	
	/**
	 * 상품이 하나 증가한다
	 */
//	public void addstock(String name, int price) {
////////		this.stock++;
//////		//고객이 제로콜라를 구매한 적이 있는지 확인
//////		//고객이 제로콜라를 구매한 적이 없다면
//////		if (this.product.getName() == null) {
//////			//고객이 가진 상품의 정보를 제로콜라로 채워준다
//////			this.product.setName(name);
//////			this.product.setPrice(price);
////////			this.product.setQuantity(1);
//////			this.product.setQuantity(VendingMachine.PRODUCT_COUNT);
//////			
//////		}
//////		//고객이 제로콜라를 구매한 적이 있다면
//////		else {
//////			//고객이 가진 제로콜라의 수량을 1개 증가 시킨다
////////			this.product.quantity++;
//////			int quantity = this.product.getQuantity();
////////			quantity++;
//////			quantity += VendingMachine.PRODUCT_COUNT;
//////			this.product.setQuantity(quantity);
//////		}
//		this.addStock(name, price, VendingMachine.PRODUCT_COUNT);	
//	}
//	
	
	// 멤버변수는 파란색 / 그 외는 지역변수 등
	
	public void addStock(String name, int price, int productCount) {
		
		// 고객이 방금 구매한 제품이 고객의 제품목록(this.productArray)에 있는지 확인한다
		// 있다면, productCount만큼 수량만 증가시킨다
		// 없다면 비어있는 인덱스를 찾아서 새롭게 할당해준다
		Product product = this.getProductByName(name);
	
			
			if (product != null) {
				int quantity = product.getQuantity();
				quantity = productCount;
				product.setQuantity(quantity);
			} else {
				int nullIndex = getNullIndex();
				if (nullIndex >= 0) {
				this.productArray[nullIndex] = new Product();
				this.productArray[nullIndex].setName(name);
				this.productArray[nullIndex].setPrice(price);
				this.productArray[nullIndex].setQuantity(productCount);
				
			 }
		 }
	 }
 
	
	// 고객만 접근할 수 있게  private
	
	protected Product getProductByName(String name) {
		for( Product product : this.productArray) {
			if (product != null && product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}
	
	private int getNullIndex() {
		for (int i = 0; i < this.productArray.length; i++) {
			if (this.productArray[i] == null) {
				return i;
			}
		} return -1;
	}
			
		public void printProducts() {
			System.out.println("고객의 잔액: " + this.wallet);
			for (Product product : this.productArray) {
				if ( product != null) {
				System.out.println("고객의 상품 수량: " + product.getQuantity());
				System.out.println("고객의 상품 이름: " + product.getName());
				}
		}
		
		

		}
}

	


