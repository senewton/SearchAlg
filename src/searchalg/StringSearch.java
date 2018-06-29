package searchalg;

import java.util.Arrays;
import java.util.Random;


public class StringSearch {
    int nrepeats = 1 ;

    StringSearch( int n, int nr ){
        
        this.nrepeats = nr ;
        
        int max = 60 ; // Maximum number of characters per string
        
        // Generate random numbers inside the array
        String arr[] = new String[n];
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
        // System.out.println("String Fibonacci:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            String s = arr[i] ; // try every element in the array and time 1m searches
            
            //starttime = System.currentTimeMillis() ;
            starttime = System.nanoTime();    
            for (int j = 0 ; j < nrepeats ; j++){
                indexfound = fibonacciSearch(arr, s) ;
            }
            //endtime = System.currentTimeMillis() ;
            endtime = System.nanoTime() ;

            System.out.println("Str;Fib;"+i+";" + s+";"+indexfound+";"+ (endtime - starttime) );
            
        }
        
        // same thing with Binary search
        // System.out.println("String Binary:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            String s = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = binSearch(arr, s) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Str;Bin;"+i+";" + s +";"+indexfound+";"+ (endtime - starttime) );            
        }
        
        // same thing with Linear search
        // System.out.println("String Linear:");
        // System.out.println("Alg;i;x;foundat;ms");
        for ( int i = 0 ;i < arr.length ; i++ )
        {
            String s = arr[i] ; // try every element in the array and time 1m searches
            
            starttime = System.nanoTime() ;
            for (int j = 0 ; j < nrepeats ; j++){
                //indexfound = Arrays.binarySearch(arr, x) ;
                indexfound = linearSearch( arr, s ) ;
            }
            endtime = System.nanoTime() ;

            System.out.println("Str;Lin;"+i+";" + s +";"+indexfound+";"+ (endtime - starttime) );            
        }
    }
    
    /* Returns index of x if present, else returns -1 */
    public static int fibonacciSearch(String arr[],String s )
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
            if ( arr[i].compareTo(s) < 0 )
            {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
 
            /* If x is greater than the value at index 
            fibMm2, cut the subarray after i+1 */
            else if (arr[i].compareTo(s) > 0)
            {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
 
            /* element found. return index */
            else return i;
        }
 
        /* comparing the last element with x */
        if(fibMMm1 == 1 && arr[offset+1].compareTo(s) == 0 )
            return offset+1;
 
        /*element not found. return -1 */
        return -1;
    }
    
    //Function to carry our linear search in array
    public static int linearSearch(String arr[],String s ){
        for (int i = 0; i < arr.length; i++) {
            if ( arr[i].compareTo(s) == 0 ){
                return i ;
            }
        }
        return -1 ;
    }
    
    //Function to carry our binary search in array    
    public static int binSearch(String arr[], String s){
    
        int l = 0, r = arr.length - 1;
        while (l <= r)
        {
            int m = l + (r-l)/2;
 
            // Check if s is present at mid
            if (arr[m].compareTo(s) == 0)
                return m;
 
            // If x greater, ignore left half
            if (arr[m].compareTo(s) < 0)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
 
        return -1 ;
    }
   
    
    // Function to fill random Strings of length 'max' in the array
    public static String randomFill( int max ){
        
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int alphlen = alphabet.length();
        
        Random rand = new Random();
        
        StringBuilder sb = new StringBuilder();
        
        for ( int i = 0 ; i < max ; i++ ){
            int randomNum = rand.nextInt(alphlen-1);
            sb.append(alphabet.charAt(randomNum));
        }
        
        return sb.toString() ;
    }
}
