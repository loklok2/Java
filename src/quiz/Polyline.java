package quiz;

public class Polyline extends Geometry {
	private int[] xArr;
	private int[] yArr;
	
	public void point(int[] xArr, int[]yArr) {
		this.xArr = xArr;
		this.yArr = yArr;
	}

	@Override
	public double getArea() {
		
		return 0;
	}

	@Override
	public double getLength() {
		
		return 0;
	}

}
