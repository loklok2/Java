package chapter5;

public class Test {

	public static void main(String[] args) {
		
		Armor armor = new Armor();
		
//		System.out.println(armor.name);
//		
//		armor.setName("CIA");
//		
//		System.out.println(armor.name);
//		
//		armor.setName2("kkk");
//		
//		System.out.println(armor.name);
//		
//		armor.setColor("yellow");
//		System.out.println(armor.color);
//		
//		armor.namename("what");
//		System.out.println(armor.name);
//		
//		armor.setWeight(200);
//		System.out.println(armor.weight);
		
		armor.setData("loklok2", 174, 82, "blue");
		System.out.println(armor.name);
		System.out.println(armor.height);
		System.out.println(armor.weight);
		System.out.println(armor.color);
	}

}

//		boolean ret = armor.takeOff();
//		if(ret) {
//			System.out.println("날 수 있습니다.");
//		}else{
//			System.out.println("날 수 없습니다.");
//		}