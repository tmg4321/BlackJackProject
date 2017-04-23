package blackjack;

public enum Suit {
	SPADES("\u2660"), DIAMONDS("\u2666"), HEARTS("\u2665"), 
	CLUBS("\u2663");
	
	String suit;
	
	Suit(String suit) {
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}
	
}
