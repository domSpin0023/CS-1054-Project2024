import java.util.*;
import java.io.*;

public class LinkedListThing
{
   public static void main(String args[])
   {
      IntNode n1 = new IntNode(1);
      IntNode n2 = new IntNode(7);
      IntNode n3 = new IntNode(3);
      IntNode n4 = new IntNode(2);
      n1.setNext(n2);
      n2.setNext(n3);
      n3.setNext(n4);
      print(n1);
      
      
   }
   
   public static void print(IntNode firstNode)
   {
      IntNode current = firstNode;
      
      while(current != null)
      {
         System.out.println(current.getData());
         current = current.getNext();
      }
   }
}