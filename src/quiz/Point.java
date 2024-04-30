package quiz;

public class Point extends Geometry {

	public Point(int x, int y) {
		super(new int[] {x}, new int[] {y});
		
	}

	@Override
	public double getArea() {
	
		return 0.0;
	}

	@Override
	public double getLength() {
		
		return 0.0;
	}
}