
package waterbasin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author van
 */
public class WaterBasin {

    /**
     * this programme solves the problem in Twitter interview
     * Link is the problem is here: https://medium.com/@bearsandsharks/i-failed-a-twitter-interview-52062fbb534b#.fzmqj6vup
     * given a vector of heights, these heights form a structure like shown in 
     * the following figure.
     * It is raining over the structure.  
     * The water in one cell always flows to the neighboring cell of least height
     * if it is not already occupied by water or a block. A sink is a cell with  
     * the property that water never flows away from it.
     *  
     * All the neighboring cells that drains into a sink are said to form a basin. 
     * Observe that the structure is thereby partitioned into basins. 
     * Determine the number of unit of water contained in all basins. 
     * In the exemple the input for this program is
     *   [2, 5, 1, 3, 1, 2, 1, 7, 7, 6] and it should output 17
     * 
     * There are two ways to input the data:
     * <ul>
     * <li> From keyboard manually
     * <li> Programme reads data from text file, in this format
     * number_space_number, i.e., 1 2
     * </ul>
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Usage: WaterBasin <path to file>, default <path_to_file> is from keyboard");
        System.out.println("The file format must be correct, the program does not validate it");
        ArrayList arr2 = new ArrayList();
        if (args.length > 0) 
        {
            try 
            {
              arr2 = WaterBasin.input_array_from_file(args[0]);
            } catch (FileNotFoundException e) 
            {
                System.err.println(args[0] + " is not a correct path to file.");
                System.exit(1);
            }
        }
        else
        {
            arr2 = WaterBasin.get_input_array();
        }
        // if you want to input the array directly in the code, follow below example
        //ArrayList arr2 = new ArrayList(Arrays.asList(2, 4, 7, -3, 5, 9, 0, 6, 3));
        int results = WaterBasin.solution(arr2);
        System.out.println("The size of filling water is: " + results);

    }
    
    /**
     *
     * @param file_path
     * @return
     * @throws FileNotFoundException
     */
    static ArrayList input_array_from_file(String file_path) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(file_path));
//        int [] tall = new int [100];
        ArrayList arr = new ArrayList();
        int i = 0;
        while(scanner.hasNextInt())
        {
//             tall[i++] = scanner.nextInt();
            arr.add(scanner.nextInt());
        }
        return arr;
    }
    
    /**
     * Get an input array from user, the input must be a non-negative number
     * @return a List contains all input values
     */
    public static ArrayList get_input_array()
    {
        Scanner sc = new Scanner(System.in);

        ArrayList arr = new ArrayList();
        System.out.println("Enter elements of the array, stop by entering a non-number character, like 'stop'");
        System.out.println("Start entering 1st element:");
//        System.out.print("Enter number 2: ");
        do 
        {
//            System.out.println(sc.nextFloat());
            arr.add(sc.nextInt());
            System.out.println("Enter next number:");
        } while (sc.hasNextInt());
        
        return arr;

    }
    /**
     * 
     * calculate the size of the sink
     * The algorithm as following:
     * <ul>
     * <li> The array is divided in two parts: left size of of the maximum (Max) element
     * and right side of the Max
     * <li> On left size of Max: find the first maxium number on this size - MaxLeft (excluding the Max)
     * <li> On right size of Max: find the first maxium number on this size - MaxRight (excluding the Max)
     * <li> Calculate the area in between MaxLeft-vs-Max, Max-vs-MaxRight
     * <li> remove the range MaxLeft-vs-Max, Max-vs-MaxRight from the array. Replace by by 
     * only one element Max.
     * <li> Remove that range from the array, put the Max value in that position
     * <li> Repeat the step above when there is only 3 elements left (i.e., left-max-right)
     * </ul>
     * @param arr ArrayList of integer values
     * @return the size of the sink
     */
    public static int solution(ArrayList arr)
    {
        // the array must have at least 3 elements, otherwise there is no water
        if (arr.size() < 3){
            return 0;
        }
        // The maximum value of the array
        int max_All = 0;
        // the maximum value on the right side to max_All
        int max_Right = 0;
        // and for the left side
        int max_Left = 0;
        
        // unit of water
        int unit_of_Water = 0;
        
        max_All = (int) Collections.max(arr);
        int index_max_All = arr.indexOf(max_All);
        //System.out.println(index_max_All);
        
        // This array is a clone of the original one and is updated after each
        // removal step
        
        ArrayList arr2 = (ArrayList)arr.clone();
        
        while(arr2.size() >=3)
        {
            // the list for element 0 to the max_All element
            List list_Left =  arr2.subList(0, index_max_All);
            
            if(list_Left.size() < 1) // when max_All is the first element of the array
            {
                max_Left = max_All;
            }
            else
                max_Left = (int) Collections.max(list_Left);
            int index_max_Left = arr2.indexOf(max_Left);
            
            // the list from the max_All+1 to the end of array
            List list_Right = arr2.subList(index_max_All+1, arr2.size());
            if (list_Right.size() < 1) // when max_All is the last element of the array
            {
                max_Right = max_All;
            }
            else 
                max_Right = (int) Collections.max(list_Right);
            int index_max_Right = arr2.lastIndexOf(max_Right);
            
            // calculate the water cube size here
            // for left side of max_All
            for (int i = index_max_Left; i < index_max_All; i ++) // exclude the index of max
            {
                unit_of_Water = unit_of_Water + (max_Left - (int)arr2.get(i));
            }
            
            // for right side of max_All
            for (int i = index_max_All + 1; i <  index_max_Right; i ++) // exclude the index of max
            {
                unit_of_Water = unit_of_Water + (max_Right - (int)arr2.get(i));
            }
            
            
            // update arr2 using a temporary array list
            ArrayList arr3 = new ArrayList();
            // add the left side
            arr3.addAll(arr2.subList(0, index_max_Left));
            // Add the max_All number in the middle
            arr3.add(max_All);
            // add the right side
            arr3.addAll(arr2.subList(index_max_Right+1, arr2.size()));
            // the things in the middle are out of the list now
            
            // copy back to arr2
            arr2 = (ArrayList) arr3.clone();
            
            //update the index and so on
            index_max_All = arr2.indexOf(max_All);
            
            
        }
        // so now, how to calculate the water size
        
        return unit_of_Water;
        
    }
 
    /**
     * This function for debug purpose only
     * @param arr array of integer
     * @param result expected result
     */
    public static void Test(int[] arr, int result)
    {
        ArrayList arr2 = new ArrayList();
        for (Object nu: arr)
        {
            arr2.add(nu);
        }
        if (result == WaterBasin.solution(arr2))
        {
            System.out.println(" This is correcct" + result);
        }
    }

}
