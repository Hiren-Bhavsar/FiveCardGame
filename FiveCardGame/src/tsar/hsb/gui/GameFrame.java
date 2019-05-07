package tsar.hsb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tsar.hsb.font.CustomFont;

public class GameFrame {

	private JFrame gameFrame;

	private JPanel menuPanel, gamePanel, scorePanel;

	private JMenuBar menuBar;

	private JLabel winsLabel, lossLabel;
	private int numWins, numLosses;

	private Card cards[];

	public GameFrame() {
		numLosses = 0;
		numWins = 0;
	}

	public void startGame(int loss, int wins) {
		numLosses = loss;
		numWins = wins;
		initFrame();
		initMenuPanel();
		initScorePanel();
		initGamePanel();

		gameFrame.add(menuPanel, BorderLayout.NORTH);
		gameFrame.add(scorePanel, BorderLayout.CENTER);
		gameFrame.add(gamePanel, BorderLayout.SOUTH);
		gameFrame.revalidate();
		gameFrame.repaint();
	}

	private void initFrame() {
		gameFrame = new JFrame("Five Card Game");
		gameFrame.setLayout(new BorderLayout());
		gameFrame.setSize(1500, 900);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	private void initMenuPanel() {
		menuPanel = new JPanel();
		menuPanel.setSize(1500, 20);
		menuPanel.setPreferredSize(new Dimension(1500, 20));
		menuPanel.setBackground(Color.CYAN);
		menuPanel.setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem newGame = new JMenuItem("New Game");

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameFrame.dispose();
				startGame(numLosses, numWins);
			}
		});

		file.add(newGame);
		file.add(exit);
		menuBar.add(file);

		menuBar.setVisible(true);

		menuPanel.add(menuBar, BorderLayout.CENTER);
		menuPanel.setVisible(true);
	}

	private void initScorePanel() {
		scorePanel = new JPanel();
		scorePanel.setSize(1500, 75);
		scorePanel.setPreferredSize(new Dimension(1500, 75));
		scorePanel.setBackground(Color.DARK_GRAY);
		scorePanel.setLayout(new BorderLayout());

		winsLabel = new JLabel("Wins: " + numWins);
		lossLabel = new JLabel("Losses: " + numLosses);

		initLabel(winsLabel);
		initLabel(lossLabel);

		scorePanel.add(winsLabel, BorderLayout.WEST);
		scorePanel.add(lossLabel, BorderLayout.EAST);
		scorePanel.setVisible(true);
	}

	private void initLabel(JLabel temp) {
		CustomFont font = new CustomFont();
		temp.setBackground(Color.BLACK);
		temp.setForeground(Color.CYAN);
		temp.setFont(font.getFont(20));
		temp.setPreferredSize(new Dimension(1500 / 2, 75));
		temp.setHorizontalAlignment(JLabel.CENTER);
		temp.setVerticalAlignment(JLabel.CENTER);
		temp.setHorizontalTextPosition(JLabel.CENTER);
		temp.setVerticalTextPosition(JLabel.CENTER);
	}

	private void initGamePanel() {
		initCards();
		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBackground(Color.DARK_GRAY);
		gamePanel.setSize(1500, 805);
		gamePanel.setPreferredSize(new Dimension(1500, 805));

		gamePanel.setVisible(true);
	}

	private void initCards() {
		cards = new Card[5];
		for (Card c : cards) {
			c = new Card();
		}
	}
}
