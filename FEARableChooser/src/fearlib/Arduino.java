package fearlib;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
	private static I2C wire;
	//if we have multiple arduinos, they could have different ports
	public Arduino(int port){
		wire = new I2C(Port.kOnboard, port);
	}
    
    //method to send data over i2c to arduino
    public void writeStringData(String writeable){
    	String WriteString = writeable;
    	char[] CharArray;
    	CharArray = WriteString.toCharArray();
    	byte[] WriteData = new byte[CharArray.length];
    	for (int i = 0; i < CharArray.length; i++) {
    		WriteData[i] = (byte) CharArray[i];
    	}
    	wire.writeBulk(WriteData);
    	WriteData = null;
    }
    
    public String readStringData(){
    	byte[] data = new byte[8];
    	char[] charArray = new char[8];
    	wire.read(8, 8, data);
    	for(int i = 0; i < 8; i++){
    		charArray[i] = (char) data[i];
    	}
    	String result = String.copyValueOf(charArray);
    	return result;
    }
}
