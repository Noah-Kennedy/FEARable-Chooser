package fearlib;

import edu.wpi.first.wpilibj.DigitalInput;

public class SwitchBoard implements FEARableSelector{
	private DigitalInput[] table;
	public static int booleansToInt(boolean[] arr){
	    int n = 0;
	    for (boolean b : arr)
	        n = (n << 1) | (b ? 1 : 0);
	    return n;
	}
	
	public SwitchBoard(DigitalInput[] switches){
		table = switches;
	}
	
	private int getState(){
	    int n = 0;
	    for (DigitalInput b : table)
	        n = (n << 1) | (b.get() ? 1 : 0);
	    return n;
	}

	@Override
	public int getChosen() {
		return getState();
	}

	@Override
	public boolean isChosen() {
		return true;
	}
}
