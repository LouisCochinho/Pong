package pong;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Inventory {

	private int maxInventorySize;
	private Image inventoryImage;
	private int posX;
	private int posY;
	private MyObject[] availableObjects;
	private int nextAvailablePlace;

	public ArrayList<MyObject> getAvailableObjects() {
		ArrayList<MyObject> returned = new ArrayList<>();
		for(MyObject b : this.availableObjects){
			if(b != null){
				returned.add(b);
			}
		}
		return returned;
	}
	
	/*public MyObject[] getAvailableObjects(){
		return this.availableObjects;
	}*/

	public List<MyObject> getActivatedObjects(){
		List<MyObject> returned = new ArrayList<MyObject>();
		ArrayList<MyObject> l = getAvailableObjects();
		for(MyObject b : l){
			if(b.isActivated()){
				returned.add(b);
			}
		}
		return returned;
	}

	public Inventory(int posX, int posY) throws SlickException{
		this.posX = posX;
		this.posY = posY;
		this.maxInventorySize = 3;
		availableObjects = new MyObject[maxInventorySize];
		this.inventoryImage = new Image("res/inventory.png");	

	}

	public void display(Graphics g) {
		g.resetTransform();
		g.drawImage(this.inventoryImage, this.posX, this.posY);
	}

	public void addObject(MyObject b, int playerNumber){
		if(!isInventoryFull()){	
			int i = this.getNextAvailablePlace();
			b.setInputKey(Util.assignInputKey(playerNumber, i));			
			this.availableObjects[i] = b;
		}
	}

	public void removeObject(MyObject b){
		for(int i = 0; i < availableObjects.length;i++){
			if(availableObjects[i] != null && availableObjects[i].equals(b)){
				availableObjects[i] = null;
			}
		}
	}

	public boolean isInventoryFull(){		
		boolean full = true;
		for (MyObject ob : availableObjects) {
			if (ob == null) {
				full = false;
				break;
			}
		}
		return full;
	}

	public int getNextAvailablePlace(){
		for(int i = 0; i<this.maxInventorySize;i++){
			if(this.availableObjects[i] == null){
				return i;
			}
		}
		return 0;
	}

	public int getMaxInventorySize(){
		return this.maxInventorySize;
	}
}

