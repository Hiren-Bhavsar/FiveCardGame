package tsar.hsb.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton {

	public enum CardFace {
		Back("CardBack.png"), Black("CardFrontBlack.png"), Red("CardFrontRed.png");

		private String url;

		CardFace(String url) {
			this.url = url;
		}

		public String url() {
			return url;
		}
	}

	private CardFace face;

	private boolean isRevealed;

	private int cardNum;

	public Card() {
		face = CardFace.Back;
		updateImage();
		this.isRevealed = false;
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public void setCardFace(CardFace face) {
		this.face = face;
	}

	public CardFace getCardFace() {
		return this.face;
	}

	public void updateImage() {
		try {
			ImageIcon imageFace = new ImageIcon(Card.class.getResource(this.face.url));
			this.setIcon(imageFace);
			this.isRevealed = true;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

}
