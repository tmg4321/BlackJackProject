package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackTable {
	private List<Card> deck = new ArrayList<>();
	private LivePlayer p;
	private Dealer d = new Dealer();
	
	public static void main(String[] args) {
		
		BlackJackTable bjt = new BlackJackTable();
		bjt.startAGame();
	}
	
	private void startAGame() {
		System.out.print("Enter your name to begin: ");
		Scanner kb = new Scanner(System.in);
		p = new LivePlayer(kb.nextLine());//construct player with name
		
		boolean keepPlaying = true;
		while (keepPlaying) {
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n"); 
			System.out.println("\nOk, " +p.getName()+ ", Let's play some "//
				+ "Blackjack!");
			//dealer deals - returns a shuttle of 2 hands and a deck
			List<List<Card>> shuttle = d.deals();
			p.setHand(shuttle.remove(0));//player's hand
			p.setScore(p.getHand().get(0).getRank().getPoints()//player's score
				+ p.getHand().get(1).getRank().getPoints());
			d.setHand(shuttle.remove(0));//dealer's hand
			d.setScore(d.getHand().get(1).getRank().getPoints());//dealer's score
			deck = shuttle.remove(0);//deck less cards in hands
			
			int whoseTurn = 0;
			p.showHand(whoseTurn);//showHand is interface method
			d.showHand(whoseTurn);//showHand is interface method
			deck = p.playsBjack(deck);//player plays on interface; returns a deck
			whoseTurn = 1;
			if (p.getScore() >= 21) {
				System.out.print("\nGame Over. Play again? y or n: ");
				String choice = kb.next().toLowerCase();
				while (!(choice.equals("y")) && !(choice.equals("n"))) {
					System.out.print("Invalid choice. \"y\" to continue, \"n\" to quit");
					choice = kb.next().toLowerCase();
				}
				if (choice.equals("y")) {
					keepPlaying = true;
				} else if (choice.equals("n")) {
					keepPlaying = false;
					break;
				}
			}
			else {
				d.playsBjack(deck);// dealer plays on interface
				if (d.getScore() > 21) {
					System.out.println("\n" +p.getName()+" is a winner!");
				}
				else if (p.getScore() > d.getScore()) {
					System.out.println("\n" +p.getName()+" is a winner!");
				}
				else if (p.getScore() == d.getScore()) {
					System.out.println("\nThat's a push - no winner");
				}
				else {
					System.out.println("\nDealer wins.");
				}
				System.out.print("\nGame Over. Play again? y or n: ");
				String choice = kb.next().toLowerCase();
				while (!(choice.equals("y")) && !(choice.equals("n"))) {
					System.out.print("Invalid choice. y to continue, n to quit");
					choice = kb.next().toLowerCase();
				}
				if (choice.equals("y")) {
					keepPlaying = true;
				} else if (choice.equals("n")) {
					keepPlaying = false;
					break;
				}
				
			}
		
		}
		System.out.println("\nGoodbye!");
		kb.close();
	}

}
