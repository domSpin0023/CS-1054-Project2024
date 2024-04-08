import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import java.util.*;
import java.io.*;

//this is an example object
public class Player extends DrawableObject
{
   //regular instance variables
   private float speedX, speedY;
   private int score;
   private int highScore;
   private boolean dead = false;
   
   //grid position
   int gridX, gridY;
   int lastGridX;
   int lastGridY;
   
   //high score system instance variables
   Scanner fileScan = null;
   FileOutputStream outputFile = null;
   PrintWriter outputWrite = null;
   
	//constructor takes in its position, sets initial values
   public Player(float x, float y)
   {
      //init simple variables
      super(x,y,25);
      speedX = 0;
      speedY = 0;
      score = 0;
      
      //init grid variables
      gridX = (int)(x/100);
      gridY = (int)(y/100);
      lastGridX = gridX;
      lastGridY = gridY;
      
      //init scanner and highScore
      try
      {
         fileScan = new Scanner(new File("proj_highScore.txt"));
         highScore = fileScan.nextInt();
      }
      catch(FileNotFoundException e)
      {}
   }
   
   
   public void act(boolean left, boolean right, boolean up, boolean down)
   {
      
      //make little guy move
      move();
      /* +- .1 is added to x or y force depending which direction bolean is true, 
         which depends on which keys are pressed */
         if(up)
            speedUp(0,-.1f);
         if(down)
            speedUp(0,.1f);
         if(left)
            speedUp(-.1f,0);
         if(right)
            speedUp(.1f,0);
         //if no key is pressed in x or y direction, slow down in that direction
         if(!up && !down)
            slowDown(false, true, .025f);
         if(!left && !right)
            slowDown(true, false, .025f);
            
         //calculate score/ highScore
         score = (int)( Math.sqrt( (300-getX())*(300-getX()) + (300-getY())*(300-getY()) ) );
         setHighScore();
   }
   
   public int getScore()
   {return score;}
   
   public int getGridX()
   {return gridX;}
   public int getGridY()
   {return gridY;}
   
   public boolean isDead(){return dead;}
   
   
   public void speedUp(float xAccel, float yAccel)
   {
      //takes in a pos or neg float by which to increase speed variables (forces)
      speedX += xAccel;
      speedY += yAccel;
      //clamps the speed variables within 5 and negative 5
      speedX = Math.max(-5,Math.min(speedX,5));
      speedY = Math.max(-5,Math.min(speedY,5));
   }
   public void slowDown(boolean slowX, boolean slowY, float decel)
   {
      //intakes two booleans
      if(slowX)
      {
         //if speed is > |.025|, then make it smaller
         //else, make it zero
         if(speedX < -0.25)
            speedX += decel;
         else if(speedX > 0.25)
            speedX -= decel;
         else
            speedX = 0;
      }
      if(slowY)
      {
         //if speed is > |.025|, then make it smaller
         //else, make it zero
         if(speedY < -.25)
            speedY += decel;
         else if(speedY > .25)
            speedY -= decel;
         else
            speedY = 0;
      }
   }
   
   public void move()
   {
      //add x and y forces to position
      setX(getX() + speedX);
      setY(getY() + speedY);
      //update grid position
      gridX = (int)(getX()/100);
      gridY = (int)(getY()/100);
   }
   
   public void dies(ArrayList<Mine> mines)
   {
      for(int i = 0; i < mines.size(); i++)
      {
         double dist = distance(mines.get(i));
         if(dist <= getSize()/2 + mines.get(i).getSize()/2)
         {
            dead = true;
            mines.get(i).setBlown(true);
         }
      }
   }
   
   public boolean gridChange()
   {
      if(lastGridX != gridX || lastGridY != gridY)
      {
         lastGridX = gridX;
         lastGridY = gridY;
         return true;
      }
      else
      {
         lastGridX = gridX;
         lastGridY = gridY;
         return false;  
      }
   }
   
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, int size, GraphicsContext gc)
   {
   
      //if alive
      if(!dead)
      {
         //draw outline and main circle
         int borderRadius = size/2+1;
         int radius = size/2;
         gc.setFill(Color.BLACK);
         gc.fillOval(x-borderRadius,y-borderRadius,size+2,size+2);
         gc.setFill(Color.YELLOW);
         gc.fillOval(x-size/2,y-size/2,size,size);
         
         //draw face
         gc.setFill(Color.BLACK);
         gc.fillOval(x-6,y-4,3,3);
         gc.fillOval(x+6,y-4,3,3);
         gc.strokeLine(x-5,y+3,x+5,y+3);
      
      }
      
      //draw score
      gc.setFill(Color.WHITE);
      gc.fillText("Score: "+Integer.toString(score),10,20);
      gc.fillText("High Score: "+Integer.toString(highScore),10,40);
   }
   
   public void setHighScore()
   {  
      //change high score when player passes it
      if(highScore < score)
      {
         highScore = score;
         try
         {
            outputFile = new FileOutputStream(new File("proj_highScore.txt"),false);
            outputWrite = new PrintWriter(outputFile);
            outputWrite.println(score);
            outputWrite.close();
         }
         catch(IOException e)
         {}
      }
      //record new high score into file
   }
}