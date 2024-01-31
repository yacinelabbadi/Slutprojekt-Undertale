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

    // Methods
    // Idea, useItem method and instanceof to call respective methods, healCharacter, equipItem etc. items don't handle that? might come back to this
    // to be able to have custom item effects without making new classes, stick instant win dog fight?

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
