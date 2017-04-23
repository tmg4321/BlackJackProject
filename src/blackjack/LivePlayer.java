package blackjack;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LivePlayer extends Player implements playsBlackjack {
	private Integer score;
	private List<Card> hand;

	public LivePlayer(String name) {
		super(name);
	}

	@Override
	public void showHand(int x) {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getName()).append(" is showing: ");
		for (Card card : this.getHand()) {
			sb.append(card.toString() + " ");
		}
		System.out.println(sb + "|\tfor a score of: " + this.score);
	}

	@Override
	public List<Card> playsBjack(List<Card> deck) {
		boolean keepGoing = true;
		while (keepGoing) {
			if (this.score > 21) {
				System.out.println("Busted! You've lost.");
				keepGoing = false;
				break;
			} else if (this.score == 21) {
				System.out.println("\nYou've won with a blackjack!");
				keepGoing = false;
				break;
			} else if (this.score < 21) {
				System.out.println("\nYour score is " + this.score//
						+ " Enter \"h\" to hit or \"s\" to stick");
				Scanner kb = new Scanner(System.in);
				String choice = kb.nextLine().toLowerCase();
				while (!(choice.equals("h")) && !(choice.equals("s"))) {
					System.out.println("Invalid entry"//
							+ "\nEnter \"h\" to hit or \"s\" to stick: ");
					choice = kb.nextLine().toLowerCase();
					kb.close();
				}
				if (choice.equals("h")) {
					Card newCard = deck.remove(0);
					this.hand.add(newCard);
					this.setScore(this.score + newCard.getRank().getPoints());
					this.showHand(0);
					keepGoing = true;
				} else if (choice.equals("s")) {
					keepGoing = false;
					break;
				}
			}
		}
		return deck;
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

}