package tsar.hsb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import tsar.hsb.font.CustomFont;
import tsar.hsb.gui.Card.CardFace;

public class GameFrame {

	private JFrame gameFrame;

	private JPanel menuPanel, gamePanel, scorePanel;

	private JMenuBar menuBar;

	private JButton userAid;

	private JLabel winsLabel, lossLabel;

	private int numWins, numLosses, userSelected;

	private final int WIDTH = 1450, HEIGHT = 750;

	private Card cards[];

	public GameFrame() {
		numLosses = 0;
		numWins = 0;
	}

	public void startGame(int loss, int wins) {
		userSelected = -1;
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
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	private void initMenuPanel() {
		menuPanel = new JPanel();
		menuPanel.setSize(WIDTH, 20);
		menuPanel.setPreferredSize(new Dimension(WIDTH, 20));
		menuPanel.setBackground(Color.DARK_GRAY);
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
		scorePanel.setSize(WIDTH, 75);
		scorePanel.setPreferredSize(new Dimension(WIDTH, 75));
		scorePanel.setBackground(Color.DARK_GRAY.darker());
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
		temp.setBackground(Color.DARK_GRAY.darker());
		temp.setOpaque(true);
		temp.setForeground(Color.CYAN);
		temp.setFont(font.getFont(20));
		temp.setPreferredSize(new Dimension(WIDTH / 6, 75));
		temp.setSize(new Dimension(WIDTH / 4, 75));
		temp.setHorizontalAlignment(JLabel.CENTER);
		temp.setHorizontalTextPosition(JLabel.CENTER);
		temp.setVerticalAlignment(JLabel.CENTER);
		temp.setVerticalTextPosition(JLabel.CENTER);
	}

	private void initGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBackground(Color.DARK_GRAY);
		gamePanel.setSize(WIDTH, 805);
		gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - (scorePanel.getHeight() + menuPanel.getHeight())));
		gamePanel.setVisible(true);

		userAid = new JButton("SELECT A CARD");
		userAid.setFont(new CustomFont().getFont(18));
		userAid.setBackground(Color.DARK_GRAY.darker());
		userAid.setForeground(Color.CYAN);
		userAid.setBounds((gamePanel.getWidth() / 2) - (150 / 2), gamePanel.getHeight() - 210, 150, 50);
		userAid.setEnabled(false);
		userAid.setFocusPainted(false);
		userAid.addActionListener(userAidListener);

		gamePanel.add(userAid);

		initCards();
	}

	private void initCards() {
		int cardW = 252, cardH = 352;
		cards = new Card[5];
		for (int x = 0; x < 5; x++) {
			cards[x] = new Card();
			cards[x].setPreferredSize(new Dimension(cardW, cardH));
			cards[x].setSize(new Dimension(cardW, cardH));
			cards[x].addActionListener(cardListener);
			cards[x].setLocation((x * (WIDTH / 5) + 17), (gamePanel.getHeight() / 2) - (int) (cardH / 1.3));
			cards[x].setVisible(true);
			cards[x].setCardNum(x);
			gamePanel.add(cards[x]);
			cards[x].setCardFace(CardFace.Red);
		}
		Random r = new Random();
		cards[r.nextInt(5)].setCardFace(CardFace.Black);
	}

	private void revealThree() {
		int x = 3;
		while (x > 0) {
			Random rand = new Random();
			int pos = rand.nextInt(5);
			if (pos != userSelected && cards[pos].getCardFace() != CardFace.Black && !cards[pos].isRevealed()) {
				cards[pos].updateImage();
				x--;
			}
		}
		userAid.setEnabled(false);
		revealFinal();
	}

	private void revealFinal() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(gameFrame, "Would you like to switch your card?", "EndGame",
				dialogButton);
		if (dialogResult == 0) {
			cards[userSelected].setBorder(UIManager.getBorder("Button.border"));
			for (Card c : cards) {
				if (!c.isRevealed() && c.getCardNum() != userSelected) {
					userSelected = c.getCardNum();
					cards[userSelected].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
					break;
				}
			}
		} else {
		}
		userAid.setEnabled(false);
		int winningCard = 0;
		for (Card c : cards) {
			if (c.getCardFace() == Card.CardFace.Black) {
				winningCard = c.getCardNum();
			}
			c.updateImage();
		}
		String s;
		if (winningCard == userSelected) {
			s = "You Win!!";
			numWins++;
		} else {
			numLosses++;
			s = "You Lose!!";
		}
		dialogButton = JOptionPane.YES_NO_OPTION;
		dialogResult = JOptionPane.showConfirmDialog(gameFrame, s + " New Game?", "NewGame?", dialogButton);
		if (dialogResult == 0) {
			gameFrame.dispose();
			startGame(numLosses, numWins);
		} else {
			gameFrame.dispose();
			System.exit(0);
		}
	}

	private ActionListener userAidListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (userSelected != -1) {
				revealThree();
			}
		}
	};

	private ActionListener cardListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (userSelected != -1)
				cards[userSelected].setBorder(UIManager.getBorder("Button.border"));
			Card temp = (Card) e.getSource();
			userSelected = temp.getCardNum();
			cards[userSelected].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
			userAid.setText("LOCK IN");
			userAid.setEnabled(true);
		}
	};
}
