package pong;

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
}
