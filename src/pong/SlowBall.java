package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SlowBall extends Neutral {

	public SlowBall(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/slowball.png");
	}
	
	@Override
	public void setImage() throws SlickException {
		this.bonusImage = new Image("res/slowballUsed.png");
	}

	@Override
	public void action(Game game) {
		// TODO Auto-generated method stub
		game.getBall().setSpeed(0.5f);
	}

	@Override
	public void revertedAction(Game game) {
		// TODO Auto-generated method stub
		game.getBall().setSpeed(0.7f);
	}
}
