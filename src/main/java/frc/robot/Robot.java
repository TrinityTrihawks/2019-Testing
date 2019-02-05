/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
  
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  AxisCamera camera;

  VictorSP victor1;
  VictorSP victor2;
  DoubleSolenoid suction;

  Joystick joystick;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    camera = CameraServer.getInstance().addAxisCamera("TestBoard Camera", RobotMap.cameraIPAddress);
    // CameraServer.getInstance().startAutomaticCapture(camera);

    victor1 = new VictorSP(RobotMap.victor1);
    victor2 = new VictorSP(RobotMap.victor2);

    joystick = new Joystick(RobotMap.joystick);

    suction = new DoubleSolenoid(RobotMap.solenoidForwardChannel, RobotMap.solenoidReverseChannel);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
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

  }




  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double victor1Power = joystick.getRawAxis(1);
    double victor2Power = joystick.getRawAxis(3);

    if(Math.abs(victor1Power) < 0.15) {
      victor1Power = 0;
    }

    
    if(Math.abs(victor2Power) < 0.15) {
      victor2Power = 0;
    }

  
    victor1.set(victor1Power);
    victor2.set(victor2Power);

    if(joystick.getRawButton(3)) {
      suction.set(DoubleSolenoid.Value.kForward);
      System.out.println("Pneumatics forward");

    } else if(joystick.getRawButton(1)) {
      suction.set(DoubleSolenoid.Value.kReverse);
      System.out.println("Pneumatics reverse");

    } else if(joystick.getRawButton(2)) {
      suction.set(DoubleSolenoid.Value.kOff);
      System.out.println("Pneumatics off");
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
