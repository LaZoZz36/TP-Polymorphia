package POO.PolymorphiaRPG.src.shop;

import POO.PolymorphiaRPG.src.items.Item;
import POO.PolymorphiaRPG.src.items.Spell;
import POO.PolymorphiaRPG.src.characters.Player;

import java.util.ArrayList;
import java.util.List;

public class Merchant {

    private List<Item> stock;

    public Merchant() {
        stock = new ArrayList<>();
    }

    // Ajouter un objet au stock
    public void addItem(Item item) {
        stock.add(item);
    }

    // Afficher les objets à vendre
    public void showStock(Player player) {
        System.out.println("=== Marchand ===");
        System.out.println("Intcoins disponibles : " + player.getIntcoins());
        for (int i = 0; i < stock.size(); i++) {
            Item item = stock.get(i);
            System.out.println(
                i + " - " + item.getName() + " (" + item.getPrice() + " intcoins)"
            );
    }
}


    // Acheter un objet
    public void sell(Player player, int index) {
        if (index < 0 || index >= stock.size()) {
            System.out.println("Objet invalide.");
            return;
        }

        Item item = stock.get(index);

        if (player.getIntcoins() < item.getPrice()) {
            System.out.println("Pas assez d'intcoins !");
            return;
        }

        player.addIntcoins(-item.getPrice());
        player.getInventory().addItem(item);

        // ✅ Si l'objet est un sort, apprendre automatiquement
        if (item instanceof Spell) {
            player.learnSpell((Spell) item);
        }

        System.out.println("Achat réussi : " + item.getName());
    }

}
