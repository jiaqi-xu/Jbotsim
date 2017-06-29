package jbotsim;


import jbotsim.event.ClockListener;


/**
 * @author JiaqiXu
 * @Date  2016.03.20
 */
public class DefaultRobotScheduler implements NodeScheduler{
   
	ClockListener postClockListener=null;
	
	ClockListener stickyRobotsListener = null;
	
	public void addPostComputeListener(ClockListener clockListener){
		this.postClockListener=clockListener;	
	}
	
	public void addStickyRobotsListener(ClockListener clockListener){
		this.stickyRobotsListener=clockListener;	
	}
    @Override
    public void onClock(Topology tp) {
          
    	//robots on the same position become 
    	if(stickyRobotsListener !=null)
    	{
    	    stickyRobotsListener.onClock();
    	    
    	    synchronized (tp) {
				
			
    	    for(Robot r: tp.robotsToMove)
        	{
        		tp.robots.remove(r);
        		tp.removeNode((Node)r);
        		
        	}
    	    tp.robotsToMove.clear();
    	    }
    	}
    	
    	
        
        //compute total times that the robots are activated
        DestMeasurements.total_Activation +=tp.robots.size();

        for (Robot robot : tp.robots)
            robot.onPreClock();//look
        

        for (Robot robot : tp.robots)
        {
            robot.onClock();//compute
            if(robot instanceof GatheringWithStickyRobots)
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
        

        for (Robot robot : tp.robots)
            robot.onPostClock();//move
    }
}
