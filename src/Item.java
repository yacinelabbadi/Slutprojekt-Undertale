// Custom class, giving objects a name and description but currently doesn't have much utility outside
// its subclasses being grouped as items, however specific methods might be added here for a more general item
// since not all items have to be healing or equipment
public class Item {
    protected String name;
    protected String description;

    // Constructor
    //public Item() {}
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Constructor to make a copy of an Item
    public Item(Item originalItem) {
        this.name = originalItem.getName();
        this.description = originalItem.getDescription();
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
