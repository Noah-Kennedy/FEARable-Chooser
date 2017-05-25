package fearlib;

public class ChooserArduino extends Arduino implements FEARableSelector{

	private FEARableChooser attachedChooser;
	private boolean recieved;
	
	public ChooserArduino(int port) {
		super(port);
		recieved = false; //the arduino has not yet sent us the selected command
	}

	/**
	 * Very important. Used to attach a chooser to this particular selector, allowing us to have two-way communication
	 * @param chooser
	 */
	public void configureAttachedChooser(FEARableChooser chooser){
		attachedChooser = chooser;
	}
	
	/**
	 * Sends the list of commands to the arduino. Must be done in order for anything to function properly
	 */
	public void sendInfo(){
		for(int i = 0; i < attachedChooser.getNumberOfCommandsInChooser(); i++){
			this.writeStringData(i + " " + attachedChooser.getCommand(i).toString() + ">>>"); // >>> denoted the end of the current command to the arduino
		}
		this.writeStringData("!!!"); //denoted to arduino that we have given it all of the commands and it can do its thing 
	}
	
	@Override
	public int getChosen() {
		int chosen = 0;
		while(!isChosen()){
			//TODO Write the code to get the transmitted data
		}
		
		return chosen;
	}

	@Override
	public boolean isChosen() {
		// TODO Auto-generated method stub
		return false;
	}

}
