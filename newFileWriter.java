import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import java.util.*;
import java.io.*;

public class newFileWriter
{
   public static void main(String args[])
   {
      try
      {
         FileWriter writer = new FileWriter("newText.txt");
         writer.write("poop");
      }
      catch(IOException e)
      {
         System.out.println("Something went wrong");
      }
   }
}