import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SideBar extends JPanel implements Serializable {
	private final int WIDTH;
	private final int HEIGHT;
	private LifeCounter life_cnt; 
	private Score score;
	private Game game;
	
	SideBar (int width, int height, LifeCounter lf_cnt, Score sc, Game g)
	{
		WIDTH = width;
		HEIGHT = height;
		score = sc;
		life_cnt = lf_cnt;
		game = g;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton but_start = new JButton("Start");
		but_start.addActionListener(new ActionListener ()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						game.start();
					}
				});
		but_start.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(but_start);
		
		JButton but_stop = new JButton("Stop");
		but_stop.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				game.stop();
			}
		});
		but_stop.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(but_stop);
		
		JButton but_save = new JButton("Save");
		but_save.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				game.save();
			}
		});
		but_save.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(but_save);
		
		JButton but_load = new JButton("Load");
		but_load.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				game.load();
			}
		});
		but_load.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(but_load);
		
		JTextField txt_lives = new JTextField();
		txt_lives.setText((new Integer(life_cnt.get_lives())).toString());
		txt_lives.setAlignmentX(Component.CENTER_ALIGNMENT);
		txt_lives.setEditable(false);
		txt_lives.setMaximumSize(new Dimension(50, 30));
		this.add(txt_lives);
		
		life_cnt.add_field_listener(txt_lives);
		
		JTextField txt_score = new JTextField("Score");
		txt_score.setText(new Integer(score.get_score()).toString());
		txt_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		txt_score.setEditable(false);
		txt_score.setMaximumSize(new Dimension(50, 30));
		this.add(txt_score);
		
		score.add_field_listener(txt_score);
		
		this.setFocusable(false);
	}

}
