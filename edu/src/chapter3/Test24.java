package chapter3;

public class Test24 {

	public static void main(String[] args) {
		String nation = "kor";
		
		
		switch(nation) {
		case "KOR":
		case "JPN":
		case "CHN":
			System.out.println("아시아");
			break;
		case "GBR":
		case "FRA":
		case "EUA":
			System.out.println("유럽");
			break;
		case "USA":
		case "CAN":
		case "MEX":
			System.out.println("아메리카");
			break;
		default:
			System.out.println("없어");
	        break;
		}
	}
}
