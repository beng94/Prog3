import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable {
	private final int STEP = 10;
	private final int SHAPE_WIDTH = 50;
	private final int SHAPE_HEIGHT = 10;
	private final int WIDTH;
	private final int HEIGHT;

	private Shape shape; 
	
	Player (int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		
		shape = new Rectangle(SHAPE_WIDTH, SHAPE_HEIGHT);
		((Rectangle)shape).setLocation(WIDTH / 2, HEIGHT - SHAPE_HEIGHT);
	}
	
	public void move_right ()
	{
		if (get_x() <= WIDTH - STEP - SHAPE_WIDTH)
		{
			Rectangle r = (Rectangle) shape;
			r.setLocation(r.getLocation().x + STEP, HEIGHT - SHAPE_HEIGHT);
		}
	}
	
	public void move_left ()
	{
		if (get_x() >= STEP)
		{
			Rectangle r = (Rectangle) shape;
			r.setLocation(r.getLocation().x - STEP, HEIGHT - SHAPE_HEIGHT);
		}
	}
	
	public int get_x () {return ((Rectangle)shape).getLocation().x; }
	
	public int get_shape_width () { return SHAPE_WIDTH; }
	
	public Shape get_shape () { return shape; }
}
