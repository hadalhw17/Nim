import java.util.Scanner;
/*
 * Student id 689995
 *  This program is a game, called Nim, which is made by Level 3 requirements.
 *  It can check all the data which is inputed
 */
public class Nim {

	static String NEXT = "Next";
	static String ERROR_INT = "You should input an integer. Try again: ";
	static String ERROR_AMOUNT = "Invalid number of bags/tokens. Try again: ";
	static String ERROR_EMPTY = "This bag is empty. Choose another one: ";
	private final static String ONEBAG = "Player 1 - choose bag: ";
	private final static String TWOBAG = "Player 2 - choose bag: ";
	public static Scanner s = new Scanner(System.in);

	// This method outputs bag status
	public static void arrOutput(int[] arr) {
		System.out.println("Bag Status: " + arr[0] + ", " + arr[1] + ", " + arr[2]);
	}
	// This method checks if all the data inputed are integers
	public static String numCheck(String num, int bags, int[] arr,int flag) {
		try {
			int c = Integer.parseInt(num);

		} catch (Exception e) {
			return ERROR_INT;// tells if number is not an integer
		}
		int c = Integer.parseInt(num);
		if (c > bags||c<1) {
			return ERROR_AMOUNT;// tells if the number inputed is bigger/smaller than amount of bags/tokens
		}
		if (flag == 0) {
			if(arr[c-1] == 0)
			{
				return ERROR_EMPTY;// tells if the bag is empty
			}		
		}
		return NEXT;// proceed
	}

	//This function all the calculations and changes data in array bags
	public static int mechanix(String numBag, String numToken, String player, int[] bags, int i) {
		int numTokenInt;
		int numBagInt;
		System.out.print(player);
		do {
			numBag = s.next();
			while (!numCheck(numBag, 3,bags,0).equals(NEXT)) {
				System.out.println(numCheck(numBag, 3,bags,0));
				numBag = s.next();
			}
		} while (!numCheck(numBag, 3,bags,0).equals(NEXT));
		System.out.print("Now choose no. of tokens: ");
		do {
			numToken = s.next();
			while (!numCheck(numToken, bags[Integer.parseInt(numBag) - 1],bags,1).equals(NEXT)) {
				System.out.println(numCheck(numToken, bags[Integer.parseInt(numBag) - 1],bags,1));
				numToken = s.next();
			}
		} while (!(numCheck(numToken, bags[Integer.parseInt(numBag) - 1],bags,1).equals(NEXT)));
		/*
		 * At this point, program knows the input of the first player and simply
		 * converts strings to integers, subtracts tokens from chosen bag, and
		 * subtracts tokens from total amount of tokens form the rest of them in
		 * all bags
		 */
		numBagInt = Integer.parseInt(numBag);
		numTokenInt = Integer.parseInt(numToken);
		bags[numBagInt - 1] -= numTokenInt;
		i -= numTokenInt;
		return i;
	}

	public static void main(String[] args) 
	{
		int[] bags = { 10, 10, 10 };
		// Initial input by players
		String numBag = new String();
		String numToken = new String();
		// This variables tells if any player inputed the last token
		boolean firstPlayer = false;
		boolean secondPlayer = false;
		// Total amount of tokens
		int i = 30;
		while (i != 0) {// Loop runs until total amount of tokens remaining is 0
			if (!secondPlayer) {// Breaks the loop if player two lost
				i = mechanix(numBag, numToken, ONEBAG, bags, i);
				arrOutput(bags);
				if (i == 0) {
					firstPlayer = true;// If player one has taken the last token, variable "firstPlayer" sets to
										// true, so at the end it can tell, that player two wins
				}
			}
			if (!firstPlayer) {// Breaks the loop if player one lost
				i = mechanix(numBag, numToken, TWOBAG, bags, i);
				arrOutput(bags);
				if (i == 0) {
					secondPlayer = true; // If player two has taken the last token, variable "secondPlayer"
											// sets to true, so at the end it can tell, that player one wins
				}
			}
		}
		if (firstPlayer) {
			System.out.println("Game Over - Player 2 wins");// Congratz to player two!!
		}
		if (secondPlayer) {
			System.out.println("Game Over - Player 1 wins");// Congratz to player one!!
		}
	}
}
