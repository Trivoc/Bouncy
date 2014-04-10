import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;

	private List<Double> x, y, vx, vy, r;
	private int ballNr = 0;
	
	private class BallModel {
	    protected double x;
	    protected double y;
	    protected double vx;
	    protected double vy;
	    protected double r;
	    
	    public BallModel(double x, double y, double vx, double vy, double r){
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.r = r;
	    }
	}
	
	List<BallModel> balls;;

	public DummyModel(double width, double height) {
	    this.areaWidth = width;
	    this.areaHeight = height;
	    balls = new ArrayList<BallModel>();
	    balls.add(new BallModel(5, 5, 5, 1, 0.8));
	    balls.add(new BallModel(2, 2, -4.3, 1, 0.8));
	}
	
	@Override
	public void tick(double deltaT) {
	    for(BallModel b : balls) {
		if (b.x <= b.r || b.x >= areaWidth - b.r) {
			b.vx *= -1;
		}
		if (b.y <= b.r || b.y >= areaHeight - b.r) {
			b.vy *= -1;

		}
		b.x += b.vx * deltaT;
		b.y += b.vy * deltaT;
		if(b.y < areaHeight - b.r){
		    b.vy -= 9.82*deltaT;
		}
		for(int i = balls.indexOf(b); i < balls.size()-1; i++){
		    BallModel c = balls.get(i+1);
		    double distance = Math.sqrt(Math.pow((b.x - c.x), 2) + Math.pow((b.y - c.y), 2));
		    	System.out.println(distance);
		    if(distance <= (c.r + b.r)){
			b.vx *= -1;
			b.vy *= -1;
			c.vx *= -1;
			c.vy *= -1;
		    }
		    
		}
	    }
	}

	@Override
	public List<Ellipse2D> getBalls() {
		List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
		for(BallModel b : balls){
		    myBalls.add(new Ellipse2D.Double(b.x -b.r, b.y - b.r, 2 * b.r, 2 * b.r));
		}
		return myBalls;
	}
}
