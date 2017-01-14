package pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Ball {
	private float posX;
	private float posY;
	private float radius;
	private boolean backX;
	private boolean backY;
	private Color color;
	private float speed;
	
	public Ball(float radius, Color color, float speed) {
		this.radius = radius;
		this.color = color;
		this.backX = false;
		this.backY = false;
		this.speed = speed;
	}

	public void display(Graphics g){
		g.setColor(color);
		g.fillOval(posX, posY, diameter(), diameter());		
	}
	
	public void move(int delta,GameContainer gc,Rectangle left_rect, Rectangle right_rect){
		// Si la balle tape le mur de gauche ou le rectangle de gauche
		if(posX < 1 
			|| (posX < left_rect.getPosX()+left_rect.getWidth() 
			&& posY > left_rect.getPosY() 
			&& posY < left_rect.getPosY()+left_rect.getHeight()))
			// La faire repartir a droite
			backX = false;
		
		// Si la balle tape le mur de droite ou le rectangle de droite
		if(posX > gc.getWidth()-diameter() 
				||(posX+diameter() > right_rect.getPosX() 
				&& posY > right_rect.getPosY() 
				&& posY < right_rect.getPosY()+right_rect.getHeight()))
			// La faire repartir a gauche
			backX = true;
		// Si la balle tape en bas
		if(posY < 1)backY = false;
		// Si la balle tape en haut
		if(posY > gc.getHeight()-diameter())backY = true;
		
		// Mouvement
		if(!backX)posX = posX + this.speed*delta;
		else posX = posX - this.speed*delta;
		if(!backY)posY = posY + this.speed*delta;
		else posY = posY - this.speed*delta;
	}
	
	public void center(GameContainer gc){
		// Place la balle au centre de l'écran
		this.posX = gc.getWidth()/2;
		this.posY = gc.getHeight()/2;
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

	public boolean isBackX() {
		return backX;
	}

	public void setBackX(boolean backX) {
		this.backX = backX;
	}

	public boolean isBackY() {
		return backY;
	}

	public void setBackY(boolean backY) {
		this.backY = backY;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	private float diameter(){
		return 2*radius;
	}
	
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public float getRadius(){
		return this.radius;
	}
}
