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

	public Card() {
		face = CardFace.Back;
		updateImage();
	}

	public void setCardFace(CardFace face) {
		this.face = face;
	}

	public CardFace getCardFace() {
		return this.face;
	}

	public void updateImage() {
		try {
			ImageIcon imageFace = new ImageIcon(this.face.url);
			this.setIcon(imageFace);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
