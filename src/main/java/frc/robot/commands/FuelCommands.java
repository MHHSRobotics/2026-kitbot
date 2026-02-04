package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.fuel.Fuel;

public class FuelCommands {
    private Fuel fuel;

    public FuelCommands(Fuel fuel) {
        this.fuel = fuel;
    }

    public Command intake() {
        return new InstantCommand(() -> fuel.intake());
    }

    public Command outtake() {
        return new InstantCommand(() -> fuel.outtake());
    }

    public Command stopIntake() {
        return new InstantCommand(() -> fuel.stopIntake());
    }

    public Command stopFeeder() {
        return new InstantCommand(() -> fuel.stopFeeder());
    }

    public Command feederForward() {
        return new InstantCommand(() -> fuel.feederForward());
    }

    public Command feederReverse() {
        return new InstantCommand(() -> fuel.feederReverse());
    }

    public Command stop(){
        return new SequentialCommandGroup(stopFeeder(),stopIntake());
    }

    // Launch sequence: spin up for configured time, then launch
    // Stops when released
    public Command launchSequence() {
        return Commands.sequence(feederForward(), Commands.waitSeconds(Fuel.Constants.spinUpSeconds), intake())
                .finallyDo(() -> stop())
                .withName("fuel launch sequence");
    }
}
