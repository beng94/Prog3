import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Point implements Serializable
{
	private final int SHAPE_WIDTH = 15;
	private final int SHAPE_HEIGHT = 15;
	private final int value;
	
	private int x;
	private int y = SHAPE_HEIGHT;
	private Shape shape;
	private Color color;
	
	Point(int width)
	{
		Random r = new Random();
		value = r.nextInt(4) + 1;
		x = r.nextInt(width - SHAPE_WIDTH);
		shape = new Ellipse2D.Double(x, SHAPE_HEIGHT, SHAPE_HEIGHT, SHAPE_HEIGHT);
		set_color();
	}
	
	private void set_color ()
	{
		switch (value)
		{
			case 1: color = Color.GRAY; break;
			case 2: color = Color.GREEN; break;
			case 3: color = Color.BLUE; break;
			case 4: color = Color.RED; break;
			default : System.out.println("Fuct"); break;
		}
	}
	
	public int move ()
	{
		y += value;
		((Ellipse2D) shape).setFrame(x, y + value, SHAPE_HEIGHT, SHAPE_HEIGHT);
		
		return y;
	}
	
	public int get_x () { return x;	}
	public Shape get_shape () { return shape; }
	public Color get_color () { return color; }
	public int get_value() { return value; }
}
