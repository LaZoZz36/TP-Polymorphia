package POO.PolymorphiaRPG.src.inventory;

import POO.PolymorphiaRPG.src.items.Item;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " ajouté à l'inventaire.");
    }

    public List<Item> getItems() {
        return items;
    }
}
