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

public class GameWin extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage sfondo;
	private JButton homeButton;
	private JButton retryButton;
	private JLabel winlb, scorelb;

	private static final int WIDTH = 630;
	private final static int HEIGHT = 400;
	public static final String SFONDO = "/immagini/star_pixel.png";

	public GameWin(CardLayoutPanel c, BreakoutGame game) {
		
		winlb = new JLabel("YOU WON!");
		winlb.setFont(new Font("Monospaced", Font.BOLD, 60));
		winlb.setForeground(Color.WHITE);
		winlb.setBounds(180, 150, WIDTH, 55);
		
		scorelb = new JLabel("Your score:" + game.getScore());
		scorelb.setFont(new Font("Monospaced", Font.BOLD, 30));
		scorelb.setForeground(Color.WHITE);
		scorelb.setBounds(200, 200, WIDTH, 55);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ImageLoader loader = new ImageLoader();

		setLayout(null);

		sfondo = loader.loadImg(SFONDO);
		
		retryButton = new JButton("Level Select");
		retryButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		retryButton.setForeground(Color.black);
		retryButton.setBackground(Color.green);
		retryButton.setBounds(120, 320, 160, 50);

		retryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.changeScreen4();
			}
		});
		this.add(retryButton);

		homeButton = new JButton("Home Menu");
		homeButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		homeButton.setForeground(Color.black);
		homeButton.setBackground(Color.red);
		homeButton.setBounds(350, 320, 160, 50);

		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.changeScreen2();
			}
		});
		this.add(homeButton);
	}

	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (sfondo != null) {
			g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
			
			this.add(winlb);
			this.add(scorelb);
		}

	}
}




