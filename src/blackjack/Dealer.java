package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer extends Player implements playsBlackjack {
	private String name;
	private Integer score;
	private List<Card> hand;

	public Dealer() {
		super("Sam StraightArrow");
	}

	public List<Card> getsADeck() {
		List<Card> deck = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			Card c = new Card(Rank.values()[i], Suit.CLUBS);
			deck.add(c);
		}
		for (int i = 0; i < 13; i++) {
			Card c = new Card(Rank.values()[i], Suit.DIAMONDS);
			deck.add(c);
		}
		for (int i = 0; i < 13; i++) {
			Card c = new Card(Rank.values()[i], Suit.HEARTS);
			deck.add(c);
		}
		for (int i = 0; i < 13; i++) {
			Card c = new Card(Rank.values()[i], Suit.SPADES);
			deck.add(c);
		}
		return deck;
	}

	public List<List<Card>> deals() {
		List<Card> deck = getsADeck();
		
		
		
		Collections.shuffle(deck);
		List<Card> playerHand = new ArrayList<>();
		List<Card> dealerHand = new ArrayList<>();

		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		List<List<Card>> shuttle = new ArrayList<>();
		shuttle.add(playerHand);
		shuttle.add(dealerHand);
		shuttle.add(deck);
		return shuttle;

	}

	@Override
	public void showHand(int x) {
		if (x == 0) {
			System.out.print("Dealer is showing: " + this.hand.get(1).getRank()//
					+ this.hand.get(1).getSuit().suit);
			System.out.println("\tscore: " + this.score);
		} else {
			StringBuilder sb = new StringBuilder();

			sb.append("Dealer is showing: ");
			for (Card card : this.getHand()) {
				sb.append(card.toString());
			}
			System.out.println(sb + "\tscore: " + this.score);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	@Override
	public List<Card> playsBjack(List<Card> deck) {
		boolean keepGoing = true;
		this.setScore(this.score + this.hand.get(0).getRank().getPoints());
		this.setScore(this.checkForAces(this.score));
		this.showHand(1);

		while (keepGoing) {
			if (this.score > 21) {
				System.out.println("Dealer busted!");
				keepGoing = false;
				break;
			} else if (this.score == 21) {
				System.out.println("Dealer has Blackjack!");
				keepGoing = false;
				break;
			} else {
				System.out.println("Dealer's score is: " + this.score);
				if (this.score >= 17) {
					System.out.println("Dealer sticks on 17 or more");
					keepGoing = false;
					break;
				} else if (this.score < 17) {
					Card newCard = deck.remove(0);
					this.hand.add(newCard);
					this.setScore(this.score + newCard.getRank().getPoints());
					this.setScore(this.checkForAces(this.score));
					this.showHand(1);
					break;
				}
			}
		}
		return deck;
	}

	@Override
	public int checkForAces(int score) {
		List<Card> temp = this.getHand();
		Card aceTest = new Card(Rank.ACE, Suit.SPADES);
		while (score > 21) {
			for (Card card : temp) {
				if (card.getRank().equals(aceTest.getRank())) {
					temp.remove(card);
					score = score - 10;
					break;
				} else {
					return score;
				}
			}
		}
		return score;
	}

}
