package org.auvua.agent;

import org.auvua.agent.tasks.Pause;
import org.auvua.agent.tasks.align.AlignToMarker;
import org.auvua.agent.tasks.align.PositionOverBuoy;
import org.auvua.agent.tasks.drive.DriveStraight;
import org.auvua.agent.tasks.drive.DriveTowardsRedBuoy;
import org.auvua.agent.tasks.drive.RemoteControlTask;
import org.auvua.agent.tasks.drive.Rotate;
import org.auvua.agent.tasks.drive.Strafe;
import org.auvua.agent.tasks.search.SearchForMarker;
import org.auvua.agent.tasks.search.SearchForRedBuoy;
import org.auvua.agent.tasks.search.SearchForRedDownBuoy;
import org.auvua.agent.tasks.special.Circumnavigate;
import org.auvua.agent.tasks.special.CircumnavigateSimple;
import org.auvua.agent.tasks.special.ResetHeading;

public class MissionFactory {
	
	public static Task buildMission(MissionType type) {
		switch(type) {
		case BUOYS_ABOVE: {
			// Start
			Task initializeGyro = new ResetHeading();
			Task initialSink = new DriveStraight(0.2, 24, 0, 6000);
			Task driveThroughGate = new DriveStraight(0.4, 24, 0, 30000);
			// Red Buoy Bop
			Task buoySearch = new SearchForRedDownBuoy(0.3, 15000);
			Task buoyAlign = new PositionOverBuoy(60000);
			Task hitBuoy1 = new DriveStraight(0, 108, 0, 10000);
			Task rise1 = new DriveStraight(0, 60, 0, 15000);
			// First Marker
			Task driveAboveMarker = new DriveStraight(0.4, 48, 0, 7000);
			Task markerSearch1 = new SearchForMarker(0.0, 600000);
			Task markerAlign1 = new AlignToMarker(60000);
			Task headingReset = new ResetHeading();
			// Maneuver Task
			Task sinkToManeuverDepth = new DriveStraight(0, 96, 0, 15000);
			Task driveToManeuver = new DriveStraight(0.4, 96, 0, 30000);
			Task driveIntoManeuver = new DriveStraight(0.1, 96, 0, 30000);
			Task circumnavigate = new CircumnavigateSimple(.1, 96, 120000);
			// Second Marker
			Task backup = new DriveStraight(0.4, 96, 0, 3000);
			Task riseAboveManeuver = new DriveStraight(0, 48, 0, 10000);
			Task driveToMarker2 = new DriveStraight(0.4, 48, 0, 6000);
			Task markerSearch2 = new SearchForMarker(0.0, 60000);
			Task markerAlign2 = new AlignToMarker(60000);
			Task resetHeading2 = new ResetHeading();
			// Drive to octogon
			Task turnRight = new DriveStraight(0.0, 48, -30.0, 10000);
			Task driveToOctogon = new DriveStraight(0.4, 48, -30.0, 100000);
			Task surface = new DriveStraight(0.0, 0.0, -30.0, 10000);
					
			initializeGyro.setSuccessTask(initialSink);
			initialSink.setSuccessTask(driveThroughGate);
			driveThroughGate.setSuccessTask(buoySearch);
			
			buoySearch.setSuccessTask(buoyAlign);
			buoyAlign.setSuccessTask(hitBuoy1);
			buoyAlign.setTimeoutTask(driveAboveMarker);
			hitBuoy1.setSuccessTask(rise1);
			
			rise1.setSuccessTask(driveAboveMarker);
			driveAboveMarker.setSuccessTask(markerSearch1);
			markerSearch1.setSuccessTask(markerAlign1);
			markerAlign1.setSuccessTask(headingReset);
			
			headingReset.setSuccessTask(sinkToManeuverDepth);
			sinkToManeuverDepth.setSuccessTask(driveToManeuver);
			driveToManeuver.setSuccessTask(driveIntoManeuver);
			driveIntoManeuver.setSuccessTask(circumnavigate);
			circumnavigate.setSuccessTask(backup);
			
			backup.setSuccessTask(riseAboveManeuver);
			riseAboveManeuver.setSuccessTask(driveToMarker2);
			driveToMarker2.setSuccessTask(markerSearch2);
			markerSearch2.setSuccessTask(markerAlign2);
			
			markerAlign2.setSuccessTask(resetHeading2);
			resetHeading2.setSuccessTask(turnRight);
			turnRight.setSuccessTask(driveToOctogon);
			driveToOctogon.setSuccessTask(surface);

			return initializeGyro;
		}
		case MARKER_ONLY: {
			Task initializeGyro = new ResetHeading();
			Task sink1 = new DriveStraight(0, 48, 0, 15000);
			Task driveStraight1 = new DriveStraight(0.4, 48, 0, 35000);
			Task driveStraight2 = new DriveStraight(0.4, 72, 0, 5000);
			Task searchForMarker = new SearchForMarker(0.4, 600000);
			Task markerAlign = new AlignToMarker(60000);
			Task headingReset = new ResetHeading();
			Task sinkToManeuverDepth = new DriveStraight(0, 96, 0, 15000);
			Task driveThroughManeuver = new DriveStraight(0.4, 96, 0, 60000);
			
			initializeGyro.setSuccessTask(sink1);
			sink1.setSuccessTask(driveStraight1);
			driveStraight1.setSuccessTask(searchForMarker);
			searchForMarker.setSuccessTask(markerAlign);
			markerAlign.setSuccessTask(driveStraight2);
			driveStraight2.setSuccessTask(headingReset);
			headingReset.setSuccessTask(sinkToManeuverDepth);
			sinkToManeuverDepth.setSuccessTask(driveThroughManeuver);
			return initializeGyro;
		}
		case TELEOP: {
			Task remoteControl = new RemoteControlTask(6000000);
			return remoteControl;
		}
		default: return null;
		}
	}
	
	public enum MissionType {
		MARKER_ONLY,
		BUOYS_ABOVE,
		BUOYS_FORWARD,
		TELEOP
	}
	
}
