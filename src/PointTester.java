import static org.junit.Assert.*;

import java.awt.geom.Ellipse2D;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PointTester {
	private Point p;
	
	@Before
	public void init()
	{
		p = new Point(100);
	}
	
	@Test
	public void ctorTest()
	{
		Assert.assertTrue(p.get_x() > 0 && p.get_x() < 100);
	}
	
	@Test
	public void valueTest()
	{
		Assert.assertTrue(p.get_value() > 0 && p.get_value() < 5);
	}
	
	@Test
	public void moveTest()
	{
		double pos = ((Ellipse2D) p.get_shape()).getY();
		double inc = p.get_value();
		p.move();
		double new_pos = ((Ellipse2D) p.get_shape()).getY();
		
		Assert.assertEquals(pos + inc, new_pos, 0.1);
	}

}
