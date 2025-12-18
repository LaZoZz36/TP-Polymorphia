package POO.PolymorphiaRPG.src.game;

import POO.PolymorphiaRPG.src.characters.Player;
import POO.PolymorphiaRPG.src.items.Armor;
import POO.PolymorphiaRPG.src.items.Potion;
import POO.PolymorphiaRPG.src.items.Spell;
import POO.PolymorphiaRPG.src.items.Weapon;
import POO.PolymorphiaRPG.src.shop.Merchant;
import POO.PolymorphiaRPG.src.items.Materia;

public class Game {

    public static void main(String[] args) {

        Player javalt = new Player("Javalt de Riv");
        Merchant merchant = new Merchant();

        merchant.addItem(new Spell("Boule de Feu", 50, 25));
        merchant.addItem(new Potion("Potion de soin", 10, 20));
        merchant.addItem(new Materia("Materia Force +5", 40, 5));
        merchant.addItem(new Materia("Materia Défense +5", 50, 5));
        merchant.addItem(new Weapon("Épée en bois", 10, 5));
        merchant.addItem(new Weapon("Épée en fer", 30, 10));
        merchant.addItem(new Weapon("Épée en or", 50, 15));
        merchant.addItem(new Armor("Bouclier en bois", 20, 5));
        merchant.addItem(new Armor("Bouclier en fer", 30, 10));
        merchant.addItem(new Armor("Bouclier en or", 40, 15));
        merchant.addItem(new Armor("Armure de bois", 20, 10));
        merchant.addItem(new Armor("Armure de fer", 40, 15));
        merchant.addItem(new Armor("Armure d'or", 60, 20));
        merchant.addItem(new Armor("Hache", 40, 15));
        merchant.addItem(new Armor("Arc", 50, 20));

        Menu menu = new Menu(javalt, merchant);
        menu.show();
    }
}
