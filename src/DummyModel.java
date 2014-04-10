import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;

	private double x, y, vx, vy, r;

	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		x = 5;
		y = 5;
		vx = 2.3;
		vy = 1;
		r = 0.5;
	}

	@Override
	public void tick(double deltaT) {
		if (x <= r || x >= areaWidth - r) {
			vx *= -0.9;
		}
		if (y <= r || y >= areaHeight - r) {
			vy *= -0.9;

		}
		x += vx * deltaT;
		y += vy * deltaT;
		if(y < areaHeight - r){
		    vy -= 9.82*deltaT;
		}
	}

	@Override
	public List<Ellipse2D> getBalls() {
		List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
		myBalls.add(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
		return myBalls;
	}
}
