import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JTextField;

public class Score implements Serializable
{
	private int score = 0;
	private JTextField field;
	
	Score() { }
	
	public void add (int add)
	{
		score += add;
		field.setText(new Integer(score).toString());
	}
	
	public void add_field_listener (JTextField f)
	{
		field = f;
	}
	
	public int get_score () { return score; }
}
