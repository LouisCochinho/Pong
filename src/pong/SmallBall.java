package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SmallBall extends Neutral {
	
	public SmallBall(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/smallball.png");
	}
	
	
	@Override
	public void setImage() throws SlickException {
		// TODO Auto-generated method stub
		this.bonusImage = new Image("res/smallballUsed.png");
	}

	@Override
	public void action(Game game) {
		// TODO Auto-generated method stub
		game.setBallSize(3);
	}

	@Override
	public void revertedAction(Game game) {
		// TODO Auto-generated method stub
		game.setBallSize(5);
	}
}
