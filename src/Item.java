public class Item {
    protected String name;
    protected String description;

    // Constructor
    public Item() {}
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
