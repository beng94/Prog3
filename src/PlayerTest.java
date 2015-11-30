import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player p;
	
	@Before
	public void init()
	{
		p = new Player(200,100);
	}
	
	@Test
	public void ctorTest()
	{
		double x = ((Rectangle) p.get_shape()).getX();		
		
		Assert.assertEquals(100, x, 0.1);
	}

	@Test
	public void moveRigthTest()
	{
		double x = ((Rectangle) p.get_shape()).getX();
		double step = 10;
		p.move_right();
		double new_x = ((Rectangle) p.get_shape()).getX();
		
		Assert.assertEquals(x + step, new_x, 0.1);
	}
	
	@Test
	public void moveLeftTest()
	{
		double x = ((Rectangle) p.get_shape()).getX();
		double step = 10;
		p.move_left();
		double new_x = ((Rectangle) p.get_shape()).getX();
		
		Assert.assertEquals(x - step, new_x, 0.1);
	}
}
