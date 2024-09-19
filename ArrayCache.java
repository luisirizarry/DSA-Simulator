public class ArrayCache {
    // store the cache entries
    private CacheEntry[] entries;
    // store the number of entries, hits, and misses
    private int numEntries;
    private int numHits;
    private int numMisses;

    // default constructor
    public ArrayCache() {
        this.entries = new CacheEntry[10];
        this.numEntries = 0;
        this.numHits = 0;
        this.numMisses = 0;
    }

    // constructor with size parameter
    public ArrayCache(int size) {
        this.entries = new CacheEntry[size];
        this.numEntries = 0;
        this.numHits = 0;
        this.numMisses = 0;
    }

    // put method
    public void put(String name, String value) {
        CacheEntry entry = new CacheEntry(name, value);
        // If the cache is not full, just shift the entries and insert the new one at the start
        if (numEntries < entries.length) {
            shiftEntries(numEntries);  // Shift up to current number of entries
            entries[0] = entry;
            numEntries++;  // Increment the count of entries
        } else {
            // Cache is full, shift everything, and replace the oldest entry (at the end)
            shiftEntries(entries.length - 1);  // Shift all entries to the right
            entries[0] = entry;  // Place the new entry at the start
        }
    }
    

    // get method
    public String get(String name) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null && entries[i].getName().equals(name)) {
                CacheEntry savedEntry = entries[i];
                shiftEntries(i);
                entries[0] = savedEntry;
                numHits++;
                return entries[0].getValue();
            }
        }
        numMisses++;
        return null;
    }
    

    // getters
    public int getNumHits() {
        return numHits;
    }

    public int getNumMisses() {
        return numMisses;
    }

    // clear method
    public void clear() {
        // reset the cache entries, number of entries, hits, and misses
        entries = new CacheEntry[entries.length];
        numEntries = 0;
        numHits = 0;
        numMisses = 0;
    }

    // check if the cache is empty
    public boolean isEmpty() {
        return numEntries == 0;
    }

    // print the cache entries and the number of hits and misses
    @Override
    public String toString(){
        String info = "Entries: " + numEntries + "\n" + " Hits: " + numHits + "\n" + " Misses: " + numMisses + "\n";
        for (CacheEntry entry : entries) {
            if(entry != null) {
                info += entry.toString() + "\n";
            }
        }
        return info;
    }

    // helper method to shift the cache entries
    private void shiftEntries(int endIdx) {
        for (int i = endIdx; i > 0; i--) {
            entries[i] = entries[i - 1];
        }
    }
    
}
