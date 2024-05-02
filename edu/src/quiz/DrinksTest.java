package quiz;

public class DrinksTest {

	abstract class Drinks{
		public abstract void Drink();
	}

	class Juice extends Drinks{

		@Override
		public void Drink() {
			System.out.println("주스를 마십니다");
		}
	}
	
	class Coffee extends Drinks{

		@Override
		public void Drink() {
		System.out.println("주스를 마십니다");
		}
	}
	
	class Beer extends Drinks{

		@Override
		public void Drink() {
		System.out.println("주스를 마십니다");
		}
	}
	class Water extends Drinks{

		@Override
		public void Drink() {
		System.out.println("주스를 마십니다");
		}
	}
	class Tea extends Drinks{

		@Override
		public void Drink() {
		System.out.println("주스를 마십니다");
		}
	}

	public void order() {
		Drinks[] drinks = new Drinks[] {
				new Juice(),
				new Coffee(),
				new Beer(),
				new Water(),
				new Tea()
		};
		
		for(Drinks drink : drinks) {
			drink.Drink();
		}
	}

	   public static void main(String[] args) {
	        DrinksTest order = new DrinksTest();
	        
	        order.order();
	    }
	}
