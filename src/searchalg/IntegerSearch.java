package searchalg;

import java.util.Arrays;
import java.util.Random;

public class IntegerSearch {
    int nrepeats = 1 ;
    
    IntegerSearch( int n, int nr ){
        this.nrepeats = nr ;
        
        // Maximum number to store inside the array
        int max  = 100000 ;
        
        // Generate random numbers inside the array
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = randomFill(max);            
            // System.out.println("generated:"+arr[i]);
        }
        
        // Sort array
        Arrays.sort(arr);        
        
        // List it out
        for (int i = 0; i < arr.length; i++) {                 
            // System.out.println("Item:"+i+ "=" +arr[i]);
        }
        
        int indexfound = -1 ;
        
        long starttime = 0 ;
        long endtime = 0 ;
        
        // use the Fibonacci search
        // System.out.println("Integer Fibonacci:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            int x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                indexfound = fibonacciSearch(arr, x) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Int;Fib;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );
            
        }
        
        // same thing with Binary search
        // System.out.println("Integer Binary:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            int x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = binSearch(arr, x) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Int;Bin;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
        
        /*
        // same thing with Linear search
        // System.out.println("Integer Linear:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            int x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                indexfound = linearSearch( arr, x ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Int;Lin;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
        */
        
        // same thing with Interpolation search
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            int x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                indexfound = interpolationSearch( arr, x ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Int;Int;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
        
    }
    
    /* Returns index of x if present, else returns -1 */
    public static int fibonacciSearch(int arr[],int x )
    {
        int n = arr.length ;
        
        /* Initialize fibonacci numbers */
        int fibMMm2 = 0; // (m-2)'th Fibonacci No.
        int fibMMm1 = 1; // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci
 
        /* fibM is going to store the smallest 
        Fibonacci Number greater than or equal to n */
        while (fibM < n)
        {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }
 
        // Marks the eliminated range from front
        int offset = -1;
 
        /* while there are elements to be inspected. 
        Note that we compare arr[fibMm2] with x. 
        When fibM becomes 1, fibMm2 becomes 0 */
        while (fibM > 1)
        {
            // Check if fibMm2 is a valid location
            int i = Math.min(offset+fibMMm2, n-1);
 
            /* If x is greater than the value at 
            index fibMm2, cut the subarray array 
            from offset to i */
            if (arr[i] < x)
            {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
 
            /* If x is greater than the value at index 
            fibMm2, cut the subarray after i+1 */
            else if (arr[i] > x)
            {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
 
            /* element found. return index */
            else return i;
        }
 
        /* comparing the last element with x */
        if(fibMMm1 == 1 && arr[offset+1] == x)
            return offset+1;
 
        /*element not found. return -1 */
        return -1;
    }    
    
    // Function to fill random numbers in the array
    public static int randomFill( int max ){
        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        return randomNum;
    }
    
    //Function to carry our linear search in array
    public static int linearSearch(int arr[],int x ){
        for (int i = 0; i < arr.length; i++) {
            if ( arr[i] == x ){
                return i ;
            }
        }
        return -1 ;
    }
    
    //Function to carry our binary search in array    
    public static int binSearch(int arr[], int x){
    
        int l = 0, r = arr.length - 1;
        while (l <= r)
        {
            int m = l + (r-l)/2;
 
            // Check if x is present at mid
            if (arr[m] == x)
                return m;
 
            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
 
        return -1 ;
    }
    
     
    // If x is present in arr[0..n-1], then returns
    // index of it, else returns -1.
    public static int interpolationSearch(int arr[], int x)
    {
        int low = 0;
        int high = arr.length - 1;
        int mid;
        while (arr[low] <= x && arr[high] >= x) 
        {
            if (arr[high] - arr[low] == 0)
                return (low + high)/2;
            /** out of range is possible  here **/
             mid = low + ((x - arr[low]) * (high - low)) / (arr[high] - arr[low]);  
 
             if (arr[mid] < x)
                 low = mid + 1;
             else if (arr[mid] > x)
                 high = mid - 1;
             else
                 return mid;
        }
        if (arr[low] == x)
            return low;
           /** not found **/
        else
            return -1; 
    }
}
