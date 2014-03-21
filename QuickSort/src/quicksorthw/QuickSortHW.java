/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksorthw;

/**
 *
 * @author Mars
 */
public class QuickSortHW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          double[] b = {7.7, 5.5, 11, 3, 16, 4.4, 20, 14, 13, 42};

        System.out.println("Array contents before sorting:");
        int i;
        for (i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
        System.out.println( );

        QuickSort.sort(b, 0, b.length-1);
        System.out.println("Sorted array values:");
        for (i = 0; i < b.length; i++)
        System.out.print(b[i] + " ");
        System.out.println( );
    }
}
