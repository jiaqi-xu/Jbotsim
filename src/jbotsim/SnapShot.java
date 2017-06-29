package jbotsim;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by JiaqiXu
 */
public class SnapShot {

    List<Robot> visRobots =  new ArrayList<Robot>();
    List<Segment> visSegments = new ArrayList<Segment>(); 
    List<Point2D> visVertexesOfObstacles = new ArrayList<Point2D>();
    
    
    void addVisibleRobots(Robot robot)
    {
    	this.visRobots = robot.getVisibleRobots();
    	
    }
       
    void addVisibleSegments(Robot robot){
    	robot.call_num=0;
        this.visSegments = robot.getVisibleSegments();
    }
    
    void addVisibleVertexes(Robot robot){
        this.visVertexesOfObstacles = robot.getVisibleVertexesOfObstacles();
    }
    
    void getSnapShot(Robot robot)
    {
       this.addVisibleRobots(robot);
       this.addVisibleSegments(robot);
       this.addVisibleVertexes(robot);
    }
}