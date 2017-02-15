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
		initObjects();
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
	
	public boolean isInventoryFull(){
		return this.inventory.isInventoryFull();
	}
	
	public int getNextAvailablePlaceInInventory(){
		return this.inventory.getNextAvailablePlace();
	}
	
	private void initObjects(){
		for(int i = 0 ; i< this.inventory.getMaxInventorySize();i++){
			MyObject obj = Util.getRandomObject(this.number,i);
			this.inventory.addObject(obj, this.number);
		}
	}
}
