package pong;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class MyObject<T> implements Action<T> {

	protected Image bonusImage;
	private int posX;
	private int posY;
	private boolean activated = false;
	private int activationTime = 10; // secondes
	private int inputKey; // Touches à presser
	

	public MyObject(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public void display(Graphics g) {
		  g.resetTransform();
		  g.drawImage(this.bonusImage, this.posX, this.posY);
	}
	
	public boolean isActivated(){
		return this.activated;
	}
	
	
	public abstract void setImage() throws SlickException;
	
	
	public int getInputKey() {
		return inputKey;
	}
	
	public void setInputKey(int inputKey){
		this.inputKey = inputKey;
	}
	
	public void activate(T obj) {
		this.setActivation(true);
		action(obj);
		try {
			setImage();
		} catch (SlickException e) {
			System.err.println("L'image n'a pas pu être chargée");
		}
	}
	
	public void desactivate(T obj){
		if(isActivationTimeFinished()){
			this.setActivation(false);
			revertedAction(obj);
		}		
	}
	
	public void decrement(){
		this.activationTime--;
	}
	
	public boolean isActivationTimeFinished(){
		return activationTime == 0;
	}
	
	protected void setActivation(boolean activated){
		this.activated = activated;
	}
}
