public class Item {
    protected String name;
    protected String description;

    // Constructor
    public Item() {}
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Methods
    // Idea, useItem method and instanceof to call respective methods, healCharacter, equipItem etc
    // to be able to have custom item effects without making new classes, stick insta win dog fight?

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
