import java.util.Scanner;

class Heap {
   private int[] nodes;
   public int length;

   public Heap() {
      nodes = new int[1000];
      length = 0;
   }

   public int getLeft(int index) {
      return nodes[index * 2];
   }

   public int getRight(int index) {
      return nodes[index * 2 + 1];
   }

   public void insert(int value) {
      length++;
      nodes[length] = value;
      heapifyUp(length);
   }

   public void delete(int index) {
      nodes[index] = nodes[length];
      nodes[length] = 0;
      length--;
      if(index == 1 || nodes[index / 2] > nodes[index])
         heapifyDown(index);
      else
         heapifyUp(index);
   }

   public void heapifyUp(int i) {
      int parent = i / 2;

      if(nodes[parent] > 0) {
         if (nodes[i] > nodes[parent]) {
            swap(i, parent);
            heapifyUp(parent);
         }
      }
   }

   public void heapifyDown(int i) {
      int largest = i; // Initialize largest as root
      int l =  2 * i;
      int r = 2 * i + 1;

      // If left child is larger than root
      if (l <= length && nodes[l] > nodes[largest])
         largest = l;

      // If right child is larger than largest so far
      if (r <= length && nodes[r] > nodes[largest])
         largest = r;

      // If largest is not root
      if (largest != i) {
         int temp = nodes[i];
         nodes[i] = nodes[largest];
         nodes[largest] = temp;

         // Recursively heapify the affected sub-tree
         heapifyDown(largest);
      }
   }

   public void update(int index, int newNode) {
      nodes[index] = newNode;
      if(index == 1 || nodes[index / 2] > nodes[index])
         heapifyDown(index);
      else
         heapifyUp(index);
   }

   public int getMax() {
      return nodes[1];
   }

   public int indexOf(int n) {
      for(int i = 1; i < length; i++) {
         if(nodes[i] == n)
            return i;
      }
      return -1;
   }

   public boolean compareTo(int[] arr) {
      for(int i = 0; i < arr.length; i++) {
         if(arr[i] != nodes[i + 1])
            return false;
      }

      return true;
   }

   public void swap(int i, int j) {
      int temp = nodes[i];
      nodes[i] = nodes[j];
      nodes[j] = temp;
   }

   public String toString() {
      String str = "";

      for(int i = 1; i <= length; i++)
         str += nodes[i] + " ";

      return str;
   }
}

public class hw5_1 {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      int n = input.nextInt(); // Get length of heap from user input
      Heap heap = new Heap(); // Initialize heap
      int[] arr = new int[n];

      for(int i = 0; i < n; i++) {
         int node = input.nextInt();
         arr[i] = node;
         heap.insert(node); // Get heap nodes from user
      }

      System.out.println(heap.compareTo(arr) ? "This is a heap." : "This is not a heap.");

      int numCmds = input.nextInt(); // Get number of commands from user

      for(int i = 0; i < numCmds; i++) {
         String cmd = input.next();
         if(cmd.equals("insert")) heap.insert(input.nextInt());
         else if(cmd.equals("delete")) heap.delete(heap.indexOf(input.nextInt()));
         else if(cmd.equals("deleteMax")) heap.delete(1);
         else if(cmd.equals("display")) System.out.println(heap.toString());
         else if(cmd.equals("displayMax")) System.out.println(heap.getMax());
         else if(cmd.equals("update")) heap.update(input.nextInt(), input.nextInt());
      }
   }
}
