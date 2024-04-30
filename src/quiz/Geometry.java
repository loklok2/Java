package quiz;   //값은 알아서 해라

abstract class Geometry{
	int[] xArr;
	int[] yArr;
	
	public Geometry(int[] xArr, int[] yArr) {
		this.xArr = xArr;
		this.yArr = yArr;
	}
	
	public abstract double getArea();
	public abstract double getLength();

}

class Point extends Geometry {


		
	public Point(int[] xArr, int[] yArr) {
		super(xArr, yArr);
		this
	}

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
