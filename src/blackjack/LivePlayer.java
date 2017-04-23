package blackjack;

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
			sb.append(card.toString());
		}
		System.out.println(sb + "\tscore: " + this.score);
	}

	@Override
	public List<Card> playsBjack(List<Card> deck) {
		boolean keepGoing = true;
		while (keepGoing) {
			this.setScore(this.checkForAces(this.score));
			if (this.score > 21) {
				System.out.println("Busted! You've lost.");
				keepGoing = false;
				break;
			} else if (this.score == 21) {
				System.out.println("Blackjack! You've won.");
				keepGoing = false;
				break;
			} else {
				System.out.println("Your score is " + this.score//
					+ " Enter \"H\" to hit or \"S\" to stick");
				Scanner kb = new Scanner(System.in);
				String choice = kb.nextLine().toLowerCase();
				while (!(choice.equals("h")) && !(choice.equals("s"))) {
					System.out.println("Invalid entry"//
						+ "\nEnter \"H\" to hit or \"S\" to stick: ");
					choice = kb.nextLine();
				}
				if (choice.equals("h")) {
					Card newCard = deck.remove(0);
					this.hand.add(newCard);
					this.setScore(this.score + newCard.getRank().getPoints());
					this.setScore(this.checkForAces(this.score));
					this.showHand(0);
					break;
				}
				else if (choice.equals("s")) {
					keepGoing = false;
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

}
