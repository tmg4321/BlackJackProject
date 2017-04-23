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
		p = new LivePlayer(kb.nextLine());
		
		boolean keepPlaying = true;
		while (keepPlaying) {
			System.out.println("\nOk, " +p.getName()+ ", Let's play some "//
				+ "blackjack");
			List<List<Card>> shuttle = d.deals();
			p.setHand(shuttle.remove(0));
			p.setScore(p.getHand().get(0).getRank().getPoints()//
				+ p.getHand().get(1).getRank().getPoints());
			d.setHand(shuttle.remove(0));
			d.setScore(d.getHand().get(1).getRank().getPoints());
			deck = shuttle.remove(0);
			
			int whoseTurn = 0;
			p.showHand(whoseTurn);
			d.showHand(whoseTurn);
			deck = p.playsBjack(deck);
			whoseTurn = 1;
			if (p.getScore() >= 21) {
				System.out.println("\nGame Over. Play again? y or n: ");
				String choice = kb.next().toLowerCase();
				while (!(choice.equals("y")) && !(choice.equals("n"))) {
					System.out.println("Invalid choice. \"y\" to continue, \"n\" to quit");
					choice = kb.nextLine().toLowerCase();
				}
				if (choice.equals("y")) {
					keepPlaying = true;
				} else if (choice.equals("n")) {
					keepPlaying = false;
					break;
				}
			}
			else {
				d.playsBjack(deck);
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
				System.out.println("\nGame Over. Play again? y or n: ");
				String choice = kb.next().toLowerCase();
				while (!(choice.equals("y")) && !(choice.equals("n"))) {
					System.out.println("Invalid choice. y to continue, n to quit");
					choice = kb.nextLine().toLowerCase();
				}
				if (choice.equals("y")) {
					keepPlaying = true;
				} else if (choice.equals("n")) {
					keepPlaying = false;
					break;
				}
				
			}
		
		}
		System.out.println("Goodbye!");
		kb.close();
	}

}
