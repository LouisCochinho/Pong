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
		this.maxInventorySize = 3;
		availableObjects = new ArrayList<MyObject>(maxInventorySize);
		this.inventoryImage = new Image("res/inventory.png");	
		
	}

	public void display(Graphics g) {
		  g.resetTransform();
		  g.drawImage(this.inventoryImage, this.posX, this.posY);
	}
	
	public void addObject(MyObject b, int playerNumber){
		if(!isInventoryFull()){			
			b.setInputKey(Util.assignInputKey(playerNumber, this.availableObjects.size()));			
			this.availableObjects.add(b);
		}
	}
	
	public void removeObject(MyObject b){
		this.availableObjects.remove(b);
		
	}
	
	public boolean isInventoryFull(){
		return availableObjects.size() >= maxInventorySize;
	}
	
	public int getNextAvailablePlace(){
		if(!isInventoryFull()){
			return this.availableObjects.size()+1;
		}else{
			return 0;
		}
	}
	
	public int getMaxInventorySize(){
		return this.maxInventorySize;
	}
}

