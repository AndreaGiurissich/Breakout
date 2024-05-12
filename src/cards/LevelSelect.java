package cards;

import main.BreakoutGame;
import main.CardLayoutPanel;
import utils.ImageLoader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelect extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage sfondo;
	private JButton l1Button;
	private JButton l2Button;
	private JButton l3Button;
	private JLabel levellb;

	private static final int WIDTH = 630;
	private final static int HEIGHT = 400;
	public static final String SFONDO = "/immagini/star_pixel.png";

	public LevelSelect(CardLayoutPanel c, BreakoutGame game) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ImageLoader loader = new ImageLoader();

		setLayout(null);

		sfondo = loader.loadImg(SFONDO);

		l1Button = new JButton("Level 1");
		l1Button.setFont(new Font("Monospaced", Font.BOLD, 20));
		l1Button.setForeground(Color.black);
		l1Button.setBackground(Color.green);
		l1Button.setBounds(120, 250, 160, 50);

		l1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				game.levelSelect(1);
				c.changeScreen1();
			}
		});
		this.add(l1Button);

		l2Button = new JButton("Level 2");
		l2Button.setFont(new Font("Monospaced", Font.BOLD, 20));
		l2Button.setForeground(Color.black);
		l2Button.setBackground(Color.yellow);
		l2Button.setBounds(350, 250, 160, 50);

		l2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				game.levelSelect(2);
				c.changeScreen1();
			}
		});
		this.add(l2Button);

		l3Button = new JButton("Level 3");
		l3Button.setFont(new Font("Monospaced", Font.BOLD, 20));
		l3Button.setForeground(Color.black);
		l3Button.setBackground(Color.red);
		l3Button.setBounds(235, 320, 160, 50);

		l3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				game.levelSelect(3);
				c.changeScreen1();
			}
		});
		this.add(l3Button);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (sfondo != null) {
			g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
			levellb = new JLabel("Select a Level");
			levellb.setFont(new Font("Monospaced", Font.BOLD, 55));
			levellb.setForeground(Color.CYAN);
			levellb.setBounds(90, 110, WIDTH, 55);
			this.add(levellb);
		}

	}
}
