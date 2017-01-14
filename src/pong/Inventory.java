package pong;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Inventory {

	// Faire classe MyImage
	private static int AVAILABLE_BONUS_SIZE = 3;
	private Image inventoryImage;
	private int posX;
	private int posY;
	private ArrayList<MyObject> availableObjects;

	public ArrayList<MyObject> getAvailableObjects() {
		return availableObjects;
	}
	
	public List<MyObject> getActivatedObjects(){
		List<MyObject> l = new ArrayList<MyObject>();
		for(MyObject b : this.availableObjects){
			if(b.isActivated()){
				l.add(b);
			}
		}
		return l;
	}

	public Inventory(int posX, int posY) throws SlickException{
		this.posX = posX;
		this.posY = posY;
		availableObjects = new ArrayList<MyObject>(AVAILABLE_BONUS_SIZE);
		this.inventoryImage = new Image("res/inventory.png");	
	}

	public void display(Graphics g) {
		  g.resetTransform();
		  g.drawImage(this.inventoryImage, this.posX, this.posY);
	}
	
	public void addObject(MyObject b, int playerNumber){
		if(this.availableObjects.size()<AVAILABLE_BONUS_SIZE){			
			b.setInputKey(Util.assignInputKey(playerNumber, this.availableObjects.size()));			
			this.availableObjects.add(b);
		}
	}
	
	public void removeObject(MyObject b){
		this.availableObjects.remove(b);
		
	}
}

