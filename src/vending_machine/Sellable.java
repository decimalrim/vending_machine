package vending_machine;

public interface Sellable {

	/**
	 * 인터페이스는 상수와 정의되지 않은 것
	 */
	// interface 에서 상수를 정의할 때
	// static final은 자동으로 적용되므로 생략한다.
//	public static final int PRODUCT_COUNT = 0;
//	public static final String MACHINE_NAME = "자판기";

	/**
	 * 한번에 구매할 수 있는 제품의 수
	 */
	public static final int PRODUCT_COUNT = 1;
	public static final String MACHINE_NAME = "자판기";
	
	public Product[] getProductArray();

	public int getMoney();

	public void setMoney(int money);

	/**
	 * 돈을 넣는 기능
	 * 
	 * @param customer    돈을 넣은 고객
	 * @param productName 구매할 제품의 이름 (제로콜라, 제로펩시, 제로스프라이트)
	 */
	public void insertMoney(Customer customer, String productName);

	/**
	 * 버튼을 누르는 기능
	 * 
	 * @param customer    버튼을 누른 고객
	 * @param productName 구매할 제품의 이름 (제로콜라, 제로펩시, 제로스프라이트)
	 */
	public void pressButton(Customer customer, String productName);

	public void pressButton(Customer customer, String productName, int orderCount);

//	/**
//	 * 고객에게 환불처리 한다 상속된 클래스에서만 사용할 수 있도록 한다
//	 * 
//	 * @param customer    환불 받을 고객
//	 * @param refundMoney 환불 받을 금액
//	 */
//	abstract void refund(Customer customer, int refundMoney);

	public void printProducts();

}
