package pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Rectangle {
	private float posX;
	private float posY;
	private float width;
	private float height;
	private Color color;
	private boolean moving_up;
	private boolean moving_down;
	private float speed;
	
	public Rectangle(float posX, float posY, float width, float height, Color color, float speed) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.color = color;
		moving_up = false;
		moving_down = false; 
		this.speed = speed;
	}

	public void display(Graphics g){
		g.setColor(color);
		g.fillRect(posX, posY, width, height);
		
	}
	
	public void move(int delta,GameContainer gc){
		if(moving_up && posY > 0){
			posY = posY - this.speed*delta;
		}
		else if(moving_down && posY+height < gc.getHeight()){
			posY = posY + this.speed*delta;
		}
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setMoving_up(boolean moving_up) {
		this.moving_up = moving_up;
	}

	public void setMoving_down(boolean moving_down) {
		this.moving_down = moving_down;
	}	
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void stopMoving(){
		moving_up = false;
		moving_down = false;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return this.speed;
	}
}
