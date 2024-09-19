public class CacheEntry {
    // store the name and value of the cache entry
    private String name;
    private String value;

    // constructor
    public CacheEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // override toString method
    @Override
    public String toString() {
        return "Name: " + name + " Value: " + value;
    }
}