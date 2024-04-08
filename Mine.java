import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import java.util.*;

//this is an example object
public class Mine extends DrawableObject
{
   private float c, t;
   private Random rand = new Random();
   private boolean isBlown = false;
	//takes in its position
   public Mine(float x, float y)
   {
      super(x,y,9);
      
      t = rand.nextFloat(0,(float)Math.PI*2f);
      setC();
   }
   
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, int size, GraphicsContext gc)
   {
      int borderRadius = size/2+1;
      int radius = size/2;
      gc.setFill(Color.BLACK);
      gc.fillOval(x-borderRadius,y-borderRadius,size+2,size+2);
      
      setC();
      gc.setFill(new Color(1,c,c,1));
      gc.fillOval(x-radius,y-radius,size,size);
   }
   
   public void setBlown(boolean blown_)
   {
      isBlown = blown_;
   }
   public boolean getBlown()
   {return isBlown;}
   
   private void setC()
   {
      t += 0.02f;
      c = (float)Math.pow((float)Math.sin(t),2);
   }
}
