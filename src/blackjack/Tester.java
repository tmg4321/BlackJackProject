package blackjack;

import java.util.List;

public class Tester {

	public static void main(String[] args) {
		Dealer d = new Dealer();
		List<List<Card>> shuttle = d.deals();
	
		System.out.println(shuttle.get(0));

	}

}
