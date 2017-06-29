package jbotsim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Haochuan Ran
 */


 public class SemiSynRobotScheduler extends DefaultRobotScheduler{

    @Override
    public void onClock(Topology tp) {

        ArrayList<Robot> robotList = new ArrayList<Robot>();
        Random random = new Random();

        robotList.addAll(tp.robots);
        int robots = robotList.size();
        for (int num = random.nextInt(robots);num > 0;num--) {
            int index = random.nextInt(num);
            robotList.remove(index);
        }


        if ( robotList != null) {
            for (Robot robot :  robotList) {
                System.out.println("Activating:" + robot.ID);
                DestMeasurements.total_Activation++;
                robot.onPreClock(); //Look
                robot.bounds -= 1;
            }
            for (Robot robot :  robotList)
            {
                robot.onClock(); //Compute
                if(robot instanceof FullVisRobot)
                {
                	if(((Destination2D)(robot.dest)).dest!=robot.getLocation())
                	{
                		DestMeasurements.total_Moves ++ ;
                		DestMeasurements.total_Distance += ((Destination2D)(robot.dest)).dest.distance(robot.getLocation());
                	}          	
                }
            }
            
            if(postClockListener!=null){
            	postClockListener.onClock();
            	
            }
            for (Robot robot :  robotList)
                robot.onPostClock(); //Move
        }
    }
    }

