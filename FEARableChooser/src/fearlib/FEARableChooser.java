package fearlib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class FEARableChooser {
	
	private ArrayList<Command> commands;	
	private DigitalInput override;
	private FEARableSelector selector;
	private Command defaultCommand;
	
	/**
	 * Constructs the chooser and initializes it with the given hardware device
	 * @param kSelector is the hardware device to be used by our chooser
	 */
	public FEARableChooser(FEARableSelector kSelector){
		selector = kSelector;
		commands = new ArrayList<Command>();
	}
	
	/**
	 * Sets the DigitalInput used as an override. Overrides when the override returns true. You need to set this up yourself in the robot class. The chooser will still function without an override
	 * @param kOverride is the DigitalInput that causes us to automatically run our default command when it returns true
	 */
	public void setOverride(DigitalInput kOverride){
		override = kOverride;
	}
	
	/**
	 * Adds a new command to the list we have
	 * @param command
	 */
	public void addCommand(Command command){
		commands.add(command);
	}
	
	/**
	 * Adds a new command to the list we have and sets it to be the default
	 * @param command
	 */
	public void addDefaultCommand(Command command){
		commands.add(command);
		defaultCommand = command;
	}
	
	/**
	 * Fetches the specified command
	 * @param index
	 * @return the command at the selected index
	 */
	public Command getCommand(int index){
		return commands.get(index);
	}
	
	/**
	 * Gets us our selected value. This does not take the override into account, and should not be used outside of this class, which is why it is currently private
	 * @return The value currently selected by our hardware configuration.
	 */
	private int getSelectorValue(){
		return selector.getChosen();
	}
	
	/**
	 * Tells us if we have selected our auto yet
	 * @return the completion status of the selection on the hardware setup
	 */
	public boolean getSelectorCompletionState(){
		return selector.isChosen();
	}
	
	/**
	 * Gets the default command
	 * @return the default command
	 */
	public Command getDefault(){
		return defaultCommand;
	}
	
	/**
	 * Tells us whether we are overriding the chooser or not
	 * @return whether we are overriding it or not
	 */
	public boolean getOverrideValue(){
		return override.get();
	}
	
	/**
	 * Tells us whether or not we have an override
	 * @return whether or not we have an override
	 */
	public boolean getOverrideExistence(){
		if(override.equals(null))	return false;
		else return true;
	}
	
	/**
	 * Used to get the number of commands currently in our chooser
	 * @return the size of our chooser's list
	 */
	public int getNumberOfCommandsInChooser(){
		return commands.size();
	}
	
	/**
	 * Takes into account the override to get us our selected command
	 * @return the command to be run
	 */
	public Command getSelected(){
		Command selected;
		if(getOverrideExistence()){
			if(getOverrideValue() == true)
				selected = defaultCommand;
		}
		selected = commands.get(getSelectorValue());
		
		return selected;
	}
}
