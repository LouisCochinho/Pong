package pong;

public class Player {
	private Rectangle rectangle;
	private Score score;
	private Inventory inventory;
	private int number;
	
	public Player(Rectangle rectangle, Score score, Inventory inventory, int number){
		this.rectangle = rectangle;
		this.score = score;
		this.inventory = inventory;
		this.number = number;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public Score getScore() {
		return score;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public void addObject(MyObject b){
		this.inventory.addObject(b, this.number);
	}
	
	
	
	public void removeObject(MyObject b){
		this.inventory.removeObject(b);
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public int getNumberOfActivatedBonus(){
		int counter = 0;
		for(MyObject b : this.getInventory().getAvailableObjects()){
			if(b.isActivated()){
				counter++;
			}
		}
		return counter;
	}
	
	public void setRectangleSize(int size){
		this.rectangle.setHeight(size);
	}
}
