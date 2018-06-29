package searchalg;

import java.util.Arrays;
import java.util.Random;

public class DoubleSearch {
    int nrepeats = 1 ;

    DoubleSearch( int n, int nr ){
        this.nrepeats = nr ;
        // Maximum number to store inside the array
        int max  = 100000 ;
        
        // Generate random numbers inside the array
        double arr[] = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = randomFill();            
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
        // System.out.println("Double Fibonacci:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            double x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                indexfound = fibonacciSearch(arr, x) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Dou;Fib;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );
            
        }
        
        // same thing with Binary search
        //System.out.println("Double Binary:");
        //System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            double x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = binSearch( arr, x ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Dou;Bin;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
        
        /*
        // same thing with Linear search
        // System.out.println("Double Linear:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            double x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = linearSearch( arr, x ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Dou;Lin;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
        */
        
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            double x = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = interpolationSearch( arr, x ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Dou;Int;"+i+";" + x+";"+indexfound+";"+ (endtime - starttime) );            
        }
    }
    
    /* Returns index of x if present, else returns -1 */
    public static int fibonacciSearch(double arr[],double x )
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
    public static double randomFill( ){
        Random rand = new Random();
        double randomNum = rand.nextDouble();
        randomNum *= 10000.0 ;
        return randomNum;
    }
    
    //Functio to carry our linear search in array
    public static int linearSearch(double arr[],double x ){
        for (int i = 0; i < arr.length; i++) {
            if ( arr[i] == x ){
                return i ;
            }
        }
        return -1 ;
    }
    
    //Function to carry our binary search in array    
    public static int binSearch(double arr[], double x){
    
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
    public static int interpolationSearch(double arr[], double x)
    {
        int low = 0;
        int high = arr.length - 1;
        int mid;
        while (arr[low] <= x && arr[high] >= x) 
        {
            if (arr[high] - arr[low] == 0)
                return (low + high)/2;
            
            /** out of range is possible  here **/
            double expr1 = x - arr[low] ;
            double expr2 = arr[high] - arr[low] ;
            
            int iexpr1 = (int) expr1 ;
            int iexpr2 = (int) expr2 ;
            mid = low + (iexpr1 * (high - low)) / iexpr2 ;  
             
             if ( mid < 0 ){
                 System.out.println("Mid:" + mid );
                 System.out.println("Low:" + low );
                 System.out.println("High:" + high );
                 System.out.println("Arr[low]:" + arr[low] );
                 System.out.println("Arr[high]:" + arr[high] );
                 System.out.println("x:" + x );
                 System.out.println("iexpr1:" + iexpr1 );
                 System.out.println("iexpr2:" + iexpr2 );
             }
 
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
