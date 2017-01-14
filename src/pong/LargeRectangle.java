package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LargeRectangle extends Bonus{
	
	public LargeRectangle(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/largeRectangle.png");
	}

	@Override
	public void setImage() throws SlickException {
		this.bonusImage = new Image("res/largeRectangleUsed.png");
	}

	@Override
	public void action(Player player) {
		// TODO Auto-generated method stub
		player.setRectangleSize(150);
	}

	@Override
	public void revertedAction(Player player) {
		// TODO Auto-generated method stub
		player.setRectangleSize(100);
	}
}
