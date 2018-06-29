package searchalg;
/* Program to compare different search algorithms */
/* Edited from home */

public class SearchAlg {

    public static void main(String[] args) {
        
        System.out.println("SearchAlg:Compare Search Algorithm");
        
        // How long to make the array
        int n = 5000 ;        
        
        // How many times to repeat each search operation
        int nrepeats = 10000 ;
        
        IntegerSearch intSearch = new IntegerSearch( n, nrepeats ) ;
        DoubleSearch doubSearch = new DoubleSearch( n, nrepeats ) ;
        StringSearch strSearch = new StringSearch( n, nrepeats ) ; 
        
    }
                 
}
