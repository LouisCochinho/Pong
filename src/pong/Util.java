package pong;

import java.util.Random;

import org.newdawn.slick.Input;

public class Util {

	public static int assignInputKey(int playerNumber, int index){
		int inputKey = Integer.MIN_VALUE;
		
		switch(playerNumber){
			case 1 : 
				switch(index){
					case 0: inputKey = Input.KEY_E;break;
					case 1: inputKey = Input.KEY_R;break;
					case 2: inputKey = Input.KEY_T;break;
				}break;
			case 2 : 
				switch(index){
					case 0: inputKey = Input.KEY_NUMPAD1;break;
					case 1: inputKey = Input.KEY_NUMPAD2;break;
					case 2: inputKey = Input.KEY_NUMPAD3;break;
				}break;
			}	
		return inputKey;
	}
	
	private static int getRandomNumber(int i){
		Random rand = new Random();
		return rand.nextInt(i)+1;
	}
	
	public static MyObject getRandomObject(int numPlayer, int numPlace){
		int numObject = getRandomNumber(8);
		MyObject returnedObject = null;
		int x = getPosX(numPlayer,numPlace);
		int y = 20;
		try{
			switch(numObject){
				case 1: returnedObject = new FastBall(x,y); break;
				case 2: returnedObject = new FastRectangle(x,y); break;
				case 3: returnedObject = new LargeBall(x,y); break;
				case 4: returnedObject = new LargeRectangle(x,y); break;
				case 5: returnedObject = new SlowBall(x,y); break;
				case 6: returnedObject = new SlowRectangle(x,y); break;
				case 7: returnedObject = new SmallBall(x,y); break;
				case 8: returnedObject = new SmallRectangle(x,y); break;
				default: break;
			}
		}catch(Exception e){
			System.err.println("Erreur lors de la création d'un bonus : ");
			e.printStackTrace();
		}		
		return returnedObject;
	}
	
	private static int getPosX(int numPlayer, int numPlace){
		return 112 + 500*(numPlayer-1)+29*numPlace;
	}
	
}
