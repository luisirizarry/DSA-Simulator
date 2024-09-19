import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {

    // simulate method
    private void simulate(ArrayCache arrayCache, String queryFile) throws FileNotFoundException {
        // create a file object
        File file = new File(queryFile);
        // create a scanner and read from the file
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                // read the line and split it into parts
                String aLine;
                aLine = scanner.nextLine();
                String[] parts = aLine.split(":");
                String name = parts[0];
                String value = parts[1];
                // Check if the cache contains the url, which should generate a hit or miss
                String tempValue = arrayCache.get(name);
                // if the cache does not contain the url, add it, and now their should be a miss
                if(tempValue == null) {
                    arrayCache.put(name, value);
                }
                // print the cache
                System.out.println(arrayCache.toString());
            }
            // close the scanner
            scanner.close();
        }

    }

    // printStats method
    private void printStats(ArrayCache arrayCache) {
        // calculate the hit rate and miss rate
        double hitRate = (double) arrayCache.getNumHits() / (arrayCache.getNumHits() + arrayCache.getNumMisses()) * 100;
        double missRate = (double) arrayCache.getNumMisses() / (arrayCache.getNumHits() + arrayCache.getNumMisses()) * 100;
        
        // print the hit rate and miss rate
        System.out.printf("Hit Rate: %.2f%%\n", hitRate);
        System.out.printf("Miss Rate: %.2f%%\n", missRate);
    }
    
    
    public static void main(String[] args) {
        String queryFile;
        int cacheSize;
        // create a scanner
        Scanner scanner = new Scanner(System.in);

        // get the query file and cache size from the user, if the cache size is negative, ask again
        do {
            System.out.println("Enter the name of the query file: ");
            queryFile = scanner.nextLine();
            System.out.println("Enter the size of the cache (Must be positive!): ");
            cacheSize = scanner.nextInt();
            // should remove the newline character incase user doesnt enter a positive number
            scanner.nextLine();
        } while (cacheSize < 0);

        scanner.close();

        // create a new ArrayCache and Simulator object
        ArrayCache arrayCache1 = new ArrayCache(cacheSize);
        Simulator simulator1 = new Simulator();
        // call the simulate and printStats methods
        try {
            simulator1.simulate(arrayCache1, queryFile);
            simulator1.printStats(arrayCache1);
        } catch (FileNotFoundException e) {
            // print an error message if the file is not found
            System.out.println("File not found");
        }
    }

}


