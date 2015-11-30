import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame
{
	private final int HEIGHT = 500;
	private final int WIDTH = 370;
	private final int GAME_WIDTH = 300;
	private final int BAR_WIDTH = 200;
	private GameSpace game_space;
	private LifeCounter life_cnt;
	private Score score;
	private SideBar side_bar;
	
	Game (String name)
	{
		this.setTitle("Potyogó Bogyók");
		this.life_cnt = new LifeCounter(5, this);
		this.score = new Score();
		this.game_space = new GameSpace(GAME_WIDTH, HEIGHT, life_cnt, score);
		this.add(game_space, BorderLayout.CENTER);
		
		this.side_bar = new SideBar(BAR_WIDTH, HEIGHT, life_cnt, score, this);
		this.add(side_bar, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(new ControlManager(game_space.get_player()));
		
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setVisible(true);
		this.toFront();
		this.setState(Frame.NORMAL);
		this.requestFocus();
		
		start();
	}
	
	public void start ()
	{
		System.out.println("Start");
		game_space.set_run(true);
		this.requestFocus();
	}
	
	public void stop ()
	{
		System.out.println("Stop");
		game_space.set_run(false);
		this.requestFocus();
	}
	
	public void save ()
	{
		System.out.println("Save");
		stop();

		try {
			ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("game.ser"));
	        out.writeObject(this.life_cnt);
	        out.writeObject(this.score);
	        out.writeObject(this.game_space);
	        out.writeObject(this.side_bar);
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		start();
		this.requestFocus();
	}
	
	public void load ()
	{
		System.out.println("Load");
		stop();

		LifeCounter lf = new LifeCounter(5, this);
		Score sc = new Score();
		GameSpace gs = new GameSpace(GAME_WIDTH, HEIGHT, lf, sc);
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("game.ser"));
	        lf = (LifeCounter) in.readObject();
	        sc = (Score) in.readObject();
	        gs = (GameSpace) in.readObject();
	        in.close();	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		     
    	this.getContentPane().removeAll();
		this.life_cnt = new LifeCounter(lf.get_lives(), this);
		this.score = sc;
		this.game_space = new GameSpace(GAME_WIDTH, HEIGHT, life_cnt, score);
		for(Point p: gs.get_points())
			this.game_space.add_point(p);
		this.add(game_space, BorderLayout.CENTER);
		
		this.side_bar = new SideBar(BAR_WIDTH, HEIGHT, life_cnt, score, this);
		this.add(side_bar, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(new ControlManager(game_space.get_player()));
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.toFront();
		this.setState(Frame.NORMAL);
		this.requestFocus();
		
		start();
	}
	
	public void lost ()
	{
		System.out.println("You lost");
		stop();
		
	    int response = JOptionPane.showConfirmDialog(this, "Game Over!\nDo you want to start a new one?", "Game Over",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        if (response == JOptionPane.NO_OPTION) 
	        {
	        	System.exit(0);
	        } 
	        else if (response == JOptionPane.YES_OPTION) 
	        {
	        	this.getContentPane().removeAll();
	    		this.setSize(WIDTH, HEIGHT);
	    		this.setVisible(true);
	    		this.toFront();
	    		this.setState(Frame.NORMAL);
	    		this.requestFocus();

	    		this.life_cnt = new LifeCounter(5, this);
	    		this.score = new Score();
	    		this.game_space = new GameSpace(GAME_WIDTH, HEIGHT, life_cnt, score);
	    		this.add(game_space, BorderLayout.CENTER);
	    		
	    		this.side_bar = new SideBar(BAR_WIDTH, HEIGHT, life_cnt, score, this);
	    		this.add(side_bar, BorderLayout.EAST);
	    		
	    		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    		this.addKeyListener(new ControlManager(game_space.get_player()));
	    		
	    		this.setSize(WIDTH, HEIGHT);
	    		this.setVisible(true);
	    		this.toFront();
	    		this.setState(Frame.NORMAL);
	    		this.requestFocus();
	    		
	    		start();
	        } 
	        else if (response == JOptionPane.CLOSED_OPTION) 
	        {
	        	System.exit(0);
	        }
	}
	
	public static void main (String[] args)
	{
		Game g = new Game("start");
	}
}
