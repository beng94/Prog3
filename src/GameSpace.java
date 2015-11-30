import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameSpace extends JPanel implements Serializable {
	private final int WIDTH;
	private final int HEIGHT;
	
	private LifeCounter life_cnt;
	private Score score;
	private Player player;
	private List<Point> points;
	private boolean run = false;
	
	GameSpace (int width, int height, LifeCounter l_cnt, Score sc)
	{
		WIDTH = width;
		HEIGHT = height;
		life_cnt = l_cnt;
		score = sc;
		player = new Player(WIDTH, HEIGHT);
		points = new ArrayList<Point>();
		
		Timer t_gen = new Timer();
		t_gen.scheduleAtFixedRate(
				new TimerTask()
				{
					@Override
					public void run()
					{
						if(run)
						{
							//Synchronized because the concurrent access to points
							synchronized(points)
							{
								points.add(new Point(WIDTH));
								redraw();
							}
						}
					}
				}, 1000, 1000);
		
		Timer t_move = new Timer();
		t_move.scheduleAtFixedRate(
				new TimerTask()
				{
					@Override
					public void run()
					{
						if(run)
						{
							synchronized(points)
							{
								move_points();		
								redraw();
							}						
						}
	
					}
				}, 100, 100);
	}
	
	public Player get_player () { return player; }
	
	private boolean within_interval (int x_player, int width, int x_point)
	{
		return (x_point >= x_player && x_point <= (x_player + width));
	}
	
	public List<Point> get_points() { return points; }
	
	public void add_point(Point p) { points.add(p); }
	
	public void move_points ()
	{	
		List<Point> remove_list = new ArrayList<Point>();
		for(int i = 0; i < points.size(); i++)
		{
			Point p = points.get(i);
			int y = p.move();
			
			if(y >= HEIGHT - 20 && within_interval(player.get_x(), player.get_shape_width(), p.get_x()))
			{
				score.add(p.get_value());
				System.out.println(score.get_score());
				remove_list.add(p);
			}
			
			if (y > HEIGHT)
			{
				life_cnt.decrease();
				remove_list.add(p);
			}
		}
		
		points.removeAll(remove_list);
	}
	
	private void redraw ()
	{
		this.repaint();
	}
	
	public void set_run (boolean b) { run = b; }
	
	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.fill(player.get_shape());
	
		synchronized(points)
		{
			
			for(int i = 0; i < points.size(); i++)
			{
				Point p = points.get(i);
				g2d.setPaint(p.get_color());
				g2d.fill(p.get_shape());
			}
		}
	}
}
