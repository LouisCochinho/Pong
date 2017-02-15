package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SlowRectangle extends Malus {

	public SlowRectangle(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/slowRectangle.png");
	}
	
	@Override
	public void setImage() throws SlickException {
		// TODO Auto-generated method stub
		this.bonusImage = new Image("res/slowRectangleUsed.png");
	}

	@Override
	public void action(Player player) {
		// TODO Auto-generated method stub
		player.getRectangle().setSpeed(0.5f);
	}

	@Override
	public void revertedAction(Player player) {
		// TODO Auto-generated method stub
		player.getRectangle().setSpeed(0.7f);
	}

}
