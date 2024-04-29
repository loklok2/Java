package quiz;

public class Quiz3_1 {
	int radius = 50;
	int height = 100;
	static double volume;
	double area;
	
	void getVolume() {
		volume = Math.PI * radius * radius * height;
	}
	void getArea() {
		area = Math.PI * radius * radius * 2+(2*Math.PI*radius) * height;
	}

public static void main(String[] args) {
	System.out.println(volume);
}
}
