package gamestore_project;

import java.io.Serializable;
public class User implements Serializable {
	// User is a class for the users of the game store,
	private String username;
	private int numberOfGames;
	private double wallet;
	private Game[] gameLibrary = new Game[50];

	public User(String n, double wallet) { //a paramitrized constructor that sets the values for the attributes
		username = n;
		numberOfGames = 0;
		this.wallet = wallet;

	}

	public void buyGame(Game g) { //this method buys a game and adds it to the library
								 //only if the game exists in the store and the library isn't full
								 //and the game doesn't already exist in library to avoid buying it twice
		boolean existsInLibrary = false;
		if (g == null) {
			System.out.println("THE GAME THAT YOU WANT TO PURCHASE DOESN'T EXIST IN THIS GAMESTORE");
			return; // to avoid using g if it's null
		}

		else {
			for (int i = 0; i < numberOfGames; i++) {
				if (gameLibrary[i].getName().equalsIgnoreCase(g.getName())) {//to check if it's already in library
					existsInLibrary = true;
					System.out.println(" \n SORRY, BUT THIS GAME ALREADY EXISTS IN YOUR LIBRARY :/ ");
				}
			}
		}

		if (existsInLibrary == false) {
			if (numberOfGames < gameLibrary.length) {//check if the game library was full
				if (g.priceAfterDiscount() <= wallet) {
					gameLibrary[numberOfGames++] = g;
					wallet = wallet - g.priceAfterDiscount();
					System.out.println("\n THANK YOU FOR PURCHASING " + g.getName() + "!!");

				} else {
					System.out.println("\n YOU DON'T HAVE ENOUGH FUNDS IN YOUR WALLET, CAN'T COMPLETE PURCHASE :( ");
				}
			} else
				System.out.println("\nSORRY, YOUR LIBRARY IS FULL");
		}

	}

	public void sendGift(User friend, Game g) { 		    //this method sends a gift to a friend if and only if:
		boolean existsInLibrary = false;       			    //1- the game doesn't already exists in the friend's library
		if (g == null || friend == null) {//this checks (2) //2- the game exists in the store and the friend's user exists
			return;							           	    //3- friend's library is not full
		}								        		    //4- user's wallet has enough funds

		else { 
			for (int i = 0; i < friend.numberOfGames; i++) { //this checks (1)
				if (friend.gameLibrary[i].getName().equalsIgnoreCase(g.getName())) {
					existsInLibrary = true;
					System.out.println(
							" \n SORRY, BUT THIS GAME ALREADY EXISTS IN " + friend.getUsername() + " LIBRARY :/ ");
				}
			}
		}

		if (existsInLibrary == false) {
			if (friend.numberOfGames < friend.gameLibrary.length) {//this checks (3)
				if (g.priceAfterDiscount() <= wallet) { //this checks (4)
					friend.gameLibrary[friend.numberOfGames++] = g;
					wallet = wallet - g.priceAfterDiscount();
					System.out.println("\n YOUR GIFT ( " + g.getName() + " ) WAS SENT SUCCESSFULLY TO "
							+ friend.getUsername() + " !!");

				} else {
					System.out.println("\n YOU DON'T HAVE ENOUGH FUNDS IN YOUR WALLET, CAN'T COMPLETE PURCHASE :( ");
				}
			} else
				System.out.println("\nTHE RECIPIENT'S LIBRARY IS FULL");
		}
	}


	public void removeGameFromLibrary(String name) { //this method removes a game from the library
		if(findGame(name)!=null){
		System.out.println(
				"\n the game is not refundable, once you remove a game you will lose access to it unless you buy it again..\n");
		System.out.println("\n do you want to proceed?...\n enter: \n \"y\"- yes, \"n\"- no \n");
		String proceed = sc.nextLine();//confirmation for the user
		if (proceed.equalsIgnoreCase("y")) {

			for (int i = 0; i < numberOfGames; i++) { //check if it exists in library
				if (gameLibrary[i].getName().equalsIgnoreCase(name)) {
					gameLibrary[i] = gameLibrary[numberOfGames - 1];
					gameLibrary[--numberOfGames] = null;
					System.out.println(name + " WAS REMOVED SUCCESSFULLY");
				}
			}
		}
	}		
  }

	public Game findGame(String name) { //this method finds a game in the library then prints its info 
		for (int i = 0; i < numberOfGames; i++) {
			if (gameLibrary[i].getName().equalsIgnoreCase(name)) {
				System.out.println(name + " HAS BEEN FOUND IN YOUR LIBRARY \n");
				System.out.println(gameLibrary[i].toString());
				return gameLibrary[i];
			}

		}
		System.out.println(name + " DOESN'T EXIST IN YOUR LIBRARY");
		return null; // if game is not found
	}


	public void displayMyLibrary(){ //displays all games in library
		if(numberOfGames!=0){
			System.out.println("\nTHESE ARE YOUR LIBRARY'S GAMES: \n ");
			for (int i = 0; i < numberOfGames; i++)
				System.out.println(gameLibrary[i]);
		}
		else
		   System.out.println("\nYOU DON'T HAVE ANY GAMES, YOUR LIBRARY IS EMPTY.");
	}

	//getters
	public String getUsername() {
		return username;
	}

	public Game[] getGameLibrary() {
		return gameLibrary;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}
	// a toString (overridden) method that will return the user's info
	public String toString() {
		String gamesInLibrary = "";
		String sWallet = wallet + "";
		for (int i = 0; i < numberOfGames; i++)
			gamesInLibrary += gameLibrary[i].toString();
		return "\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n USERNAME: " + username + "\n THE AMOUNT OF FUNDS IN WALLET: "
				+ sWallet.substring(0, (sWallet.indexOf('.') + 2)) + "\n THE NUMBER OF GAMES IN LIBRARY IS: ( "
				+ numberOfGames + " ) \n \n THE GAMES IN LIBRARY ARE: : \n" + gamesInLibrary
				+ " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	}
}