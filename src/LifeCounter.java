import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JTextField;

public class LifeCounter implements Serializable {
	private int lives;
	private JTextField field;
	private Game game;
	
	LifeCounter (int lives, Game g)
	{
		this.lives = lives;
		this.game = g;
	}
	
	public void decrease ()
	{
		lives--;
		field.setText(new Integer(lives).toString());
		if(lives <= 0)
		{
			game.stop();
			game.lost();
		}
	}
	
	public int get_lives () { return lives; }
	
	public void add_field_listener (JTextField f)
	{
		field = f;
	}
}
