import java.util.Scanner;

/*
 * Student 
 *  This program is a game, called Nim, which is made by Level 3 requirements.
 *  It can check all the data which is inputed
 */
public class Nim {
	
	//This method outputs bag status
	public static void arrOutput(int[] arr) {
		System.out.println("Bag Status: "+ arr[0] + " " + arr[1] + " " + arr[2]);
	}
	
    //This method checks if all the data inputed are integers
	public static boolean numCheck(String num) {
		try {
			int c = Integer.parseInt(num);

		} catch (NumberFormatException e) {
			System.out.println("No!!!");
			return false;

		}
		return true;
	}

	public static void main(String[] args) {

		int[] bags = { 10, 10, 10 };
		
		Scanner s = new Scanner(System.in);

		//Initial input by players
		String numBag = new String();
		String numToken = new String();

		//Input by players, converted from string values
		int numBagInt;
		int numTokenInt;

		// This variables tells if any player inputed the last token
		boolean firstPlayer = false;
		boolean secondPlayer = false;

		//Total amount of tokens
		int i = 30;

		while (i != 0) {//Loop runs until total amount of tokens remaining is 0

			if (!secondPlayer) {//Breaks the loop if player two lost
				
				System.out.print("Player 1 - choose bag: ");
				do {
					
					numBag = s.nextLine();

					while (!numCheck(numBag)) {
						
						System.out.println("You should input integer. Try again: ");
						numBag = s.nextLine();
					}
					
					while (Integer.parseInt(numBag) > 3) {
						
						System.out.println("You should input integer less than 3. Try again: ");
						numBag = s.nextLine();
					}
					
					while (bags[Integer.parseInt(numBag)-1]==0){
						
						System.out.println("This bag is empty. Choose another one: ");
						numBag = s.nextLine();
						
					}

				} while (!numCheck(numBag));

				System.out.print("Now choose no. of tokens: ");
				do {
					numToken = s.nextLine();
					
					while (!numCheck(numToken)) {
						
						System.out.println("You should input integer. Try again: ");
						numToken = s.nextLine();
					}
					
					while (Integer.parseInt(numToken) > bags[Integer.parseInt(numBag) - 1]) {
						
						System.out.println("You should input integer less than " + bags[Integer.parseInt(numBag) - 1]
								+ ". Try again: ");
						numToken = s.nextLine();
					}
					
				} while (!numCheck(numToken));
				
				/*At this point, program knows the input of the first player and simply converts strings to integers, subtracts tokens from chosen bag,
				* and subtracts tokens from total amount of tokens form the rest of them in all bags
				*/
				numBagInt = Integer.parseInt(numBag);
				
				numTokenInt = Integer.parseInt(numToken);
				
				bags[numBagInt - 1] -= numTokenInt;
				
				i -= numTokenInt;

				arrOutput(bags);

				if (i == 0) {
					firstPlayer = true;//If player one has taken the last token, variable "firstPlayer" sets to true, so at the end it can tell, that player two wins 
				}
			}
			if (!firstPlayer) {//Breaks the loop if player one lost
				
				System.out.print("Player 2 - choose bag: ");
				
				do {
					
					numBag = s.nextLine();
					
					while (!numCheck(numBag)) {
						
						System.out.println("You should input integer. Try again: ");
						numToken = s.nextLine();
					}
					
					while (Integer.parseInt(numBag) > 3) {
						
						System.out.println("You should input integer less than 3. Try again: ");
						numBag = s.nextLine();
					}
					
                    while (bags[Integer.parseInt(numBag)-1]==0){
						
						System.out.println("This bag is empty. Choose another one: ");
						numBag = s.nextLine();
						
					}

				} while (!numCheck(numBag));

				System.out.print("Now choose no. of tokens: ");
				
				do {
					
					numToken = s.nextLine();
					
					while (!numCheck(numToken)) {
						
						System.out.println("You should input integer. Try again: ");
						
						numToken = s.nextLine();
					}
					
					while (Integer.parseInt(numToken) > bags[Integer.parseInt(numBag) - 1]) {
						
						System.out.println("You should input integer less than " + bags[Integer.parseInt(numBag) - 1]
								+ ". Try again: ");
						numToken = s.nextLine();
					}

				} while (!numCheck(numToken));
				
				/*At this point, program knows the input of the second player and simply converts strings to integers, subtracts tokens from chosen bag,
				* and subtracts tokens from total amount of tokens form the rest of them in all bags
				*/
				numBagInt = Integer.parseInt(numBag);
				
				numTokenInt = Integer.parseInt(numToken);
				
				bags[numBagInt - 1] -= numTokenInt;

				i -= numTokenInt;

				arrOutput(bags);

				if (i == 0) {
					
					secondPlayer = true; //If player two has taken the last token, variable "secondPlayer" sets to true, so at the end it can tell, that player one wins 
					
				}
			}
		}

		if (firstPlayer) {
			System.out.println("Game Over - Player 2 wins");//Congratz to player two!!
		}
		
		if (secondPlayer) {
			System.out.println("Game Over - Player 1 wins");//Congratz to player one!!
		}
	}
}
