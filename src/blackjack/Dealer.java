package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
			System.out.print("\nDealer is showing: " + this.hand.get(1).getRank()//
					+ this.hand.get(1).getSuit().suit);
			System.out.println("\t|for a score of: " + this.score);
		} else {
			StringBuilder sb = new StringBuilder();

			sb.append("\nDealer is showing: ");
			for (Card card : this.getHand()) {
				sb.append(card.toString());
			}
			System.out.println(sb + "\t|for a score of: " + this.score);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		if (score <= 21) {
			return score;
		} 
		else if (score > 21) {//test for & adjust score for aces
			Integer tempScore = score;
			List<Card> temp = this.getHand();
			Card aceTest = new Card(Rank.ACE, Suit.SPADES);
			Iterator<Card> it = temp.iterator();
				while(tempScore > 21 && it.hasNext()) {
					Card c = it.next();
					if (c.getRank().equals(aceTest.getRank())) {
						temp.remove(c);
						tempScore = tempScore - 10;
					}
				}
			return score = tempScore;
		} else {
			return score;
		}
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
		this.setScore(this.score + this.hand.get(0).getRank().getPoints());
		this.showHand(1);

		boolean keepGoing = true;
		while (keepGoing) {
			if (this.score > 21) {
				System.out.println("\nDealer busted!");
				keepGoing = false;
				break;
			} else if (this.score == 21) {
				System.out.println("\nDealer has Blackjack!");
				keepGoing = false;
				break;
			} else {
				System.out.println("Dealer's score is: " + this.score);
				if (this.score >= 17) {
					System.out.println("\nDealer sticks on 17 or more");
					keepGoing = false;
					break;
				} 
				else {
					Card newCard = deck.remove(0);
					this.hand.add(newCard);
					this.setScore(this.score + newCard.getRank().getPoints());
					this.showHand(1);
				}
			}
		}
		return deck;
	}

}
