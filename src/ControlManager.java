import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlManager implements KeyListener 
{
	private Player player;
	
	ControlManager (Player p)
	{
		this.player = p;
	}
	
	public void keyTyped (KeyEvent e)
	{
		switch(e.getKeyChar())
		{
		case 'a':
			player.move_left();
			break;
		case 'd':
			player.move_right();
			break;
		}
	}
	
	public void keyPressed (KeyEvent e) { }
	
	public void keyReleased (KeyEvent e) { }
}
