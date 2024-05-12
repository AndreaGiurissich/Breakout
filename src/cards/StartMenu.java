package cards;

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
import javax.swing.JPanel;

public class StartMenu extends JPanel{

	private static final long serialVersionUID = 1L;

	private BufferedImage sfondo;
	private JButton startButton;
	private JButton quitButton;
	
	private static final int WIDTH = 630;
	private final static int HEIGHT = 400;
	public static final String SFONDO = "/immagini/Sfondo.png";
	
	public StartMenu(CardLayoutPanel c) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ImageLoader loader = new ImageLoader();
		
		setLayout(null);
		
		
		sfondo = loader.loadImg(SFONDO);
		
		startButton = new JButton("Start Game");
		startButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		startButton.setForeground(Color.black);
		startButton.setBackground(Color.white);
		startButton.setBounds(120, 320, 160, 50);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				c.changeScreen4();
			}
		});
		this.add(startButton);
		
		
		
		quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		quitButton.setForeground(Color.black);
		quitButton.setBackground(Color.red);
		quitButton.setBounds(350, 320, 160, 50);
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		this.add(quitButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(sfondo != null) {
			g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
		}
		
	}
}
