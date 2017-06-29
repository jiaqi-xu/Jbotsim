package jbotsim;

import java.util.ArrayList;
import java.util.Random;

public class AsynchrRobotScheduler extends DefaultRobotScheduler {

	@Override
	public void onClock(Topology tp) {

		ArrayList<Robot> robotList = new ArrayList<Robot>();
		robotList.addAll(tp.robots);
		Random random = new Random();
		int nodes = robotList.size();
		for (int num = random.nextInt(nodes); num > 0; num--) {
			int index = random.nextInt(num);
			robotList.remove(index);
		}
		for (Robot robot : robotList) {

			if (robot.moving == false) {
				System.out.println("Activating:" + robot.ID);
				DestMeasurements.total_Activation++;
				robot.onPreClock(); // Look
			}
		}
		for (Robot robot : robotList) {
			if (robot.moving == false) {
				robot.onClock(); // Compute

				if (robot instanceof FullVisRobot) {
					if (((Destination2D) (robot.dest)).dest != robot
							.getLocation()) {
						DestMeasurements.total_Moves++;
						DestMeasurements.total_Distance += ((Destination2D) (robot.dest)).dest
								.distance(robot.getLocation());
					}
				}
			}
		}
		if (postClockListener != null) {
			postClockListener.onClock();

		}
		for (Robot robot : robotList)
			robot.asynchMove(); // Move

	}
}
