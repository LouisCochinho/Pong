package pong;

import org.newdawn.slick.Graphics;

public class Score {

	private float posX;
	private float posY;
	private Integer score;
	
	public Score(float posX, float posY){
		this.score = 0;
		this.posX = posX;
		this.posY = posY;
	}

	public void increment(){
		this.score++;
	}
	
	public void display(Graphics g){
		g.drawString(score.toString(), posX, posY);		
	}
	
	public Integer getValue(){
		return this.score;
	}
}
