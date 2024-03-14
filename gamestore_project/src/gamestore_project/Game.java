package gamestore_project;

public abstract class Game {

	protected String name;
	protected String publisher;
	protected double price;

	public Game(String name, String publisher, double price) {
		this.name = name;
		this.publisher = publisher;
		this.price = price;
	}

	public abstract double priceAfterDiscount();

	public String getName() {
		return name;
	}

	public String getPublisher() {
		return publisher;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		 String sPrice =priceAfterDiscount() + "";
		return "\n ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~ \n GAME NAME: " + name + "\n GAME PUBLISHER: " + publisher + "\n GAME PRICE: " + price + "\n PRICE AFTER DISCOUNT: " + sPrice.substring(0,(sPrice.indexOf('.')+2))+ "\n ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~ \n";
	}
}
