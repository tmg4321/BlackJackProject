package blackjack;

import java.util.List;

public interface playsBlackjack {

	List<Card> playsBjack(List<Card> deck);
	
	void showHand(int x);
	
	int checkForAces(int score);
}
