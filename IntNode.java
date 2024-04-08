public class IntNode
{
   private int data;
   private IntNode next;
   
   public IntNode(int datax)
   {
      data = datax;
   }
   public int getData()
   {
      return data;
   }
   public void setData(int newData)
   {
      data = newData;
   }
   public IntNode getNext()
   {
      return next;
   }
   public void setNext(IntNode newNext)
   {
      next = newNext;   
   }
}