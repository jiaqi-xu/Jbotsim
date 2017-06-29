package jbotsim;

import java.awt.geom.Point2D;

/**
 * @author JiaqiXu
 * @Date  2016.03.20
 */
public class Destination2D extends Destination {
   public Point2D dest = null;

   public Destination2D(){}

   public Destination2D(Point2D dest) {
      this.dest = dest;
   }
}
