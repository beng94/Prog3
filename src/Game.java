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
	private final int WIDTH = 500;
	private final int GAME_WIDTH = 300;
	private final int BAR_WIDTH = 200;
	private GameSpace game_space;
	private LifeCounter life_cnt;
	private Score score;
	private SideBar side_bar;
	
	Game ()
	{
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
	
		//TODO: forbid resize
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
	        out.writeObject(this);
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
		
		Game g = null;
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("game.ser"));
	        g = (Game) in.readObject();
	        in.close();	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        System.out.println(g.game_space.get_points().size());
    	
        getContentPane().removeAll();
		this.life_cnt = g.life_cnt;
		this.score = g.score;
		this.game_space = g.game_space;
		this.add(game_space, BorderLayout.CENTER);
		
		this.side_bar = g.side_bar;
		this.add(side_bar, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(new ControlManager(game_space.get_player()));
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.toFront();
		this.setState(Frame.NORMAL);

        start();
		this.requestFocus();
	}
	
	public void lost ()
	{
		System.out.println("You lose");
		stop();
		
	    int response = JOptionPane.showConfirmDialog(null, "Game Over!\nDo you want to start a new one", "Gane Over",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        if (response == JOptionPane.NO_OPTION) 
	        {
	        	System.exit(0);
	        } 
	        else if (response == JOptionPane.YES_OPTION) 
	        {
	        	getContentPane().removeAll();
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
		Game g = new Game();
	}
}
