
package org.usfirst.frc.team4786.robot;

import org.usfirst.frc.team4786.robot.commands.BlueLight;
import org.usfirst.frc.team4786.robot.commands.GreenLight;
import org.usfirst.frc.team4786.robot.commands.RedLight;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import fearlib.Arduino;
import fearlib.FEARableChooser;
import fearlib.SwitchBoard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Arduino leds = new Arduino(8);
	public static DigitalInput[] board = {new DigitalInput(0), new DigitalInput(1), new DigitalInput(2)};
	public static SwitchBoard selector = new SwitchBoard(board);

	Command autonomousCommand;
	FEARableChooser chooser = new FEARableChooser(selector);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.setOverride(new DigitalInput(7));
		chooser.addDefaultCommand(new GreenLight());
		chooser.addCommand(new RedLight());
		chooser.addCommand(new BlueLight());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
