package POO.PolymorphiaRPG.src.characters;

import POO.PolymorphiaRPG.src.inventory.Inventory;
import POO.PolymorphiaRPG.src.items.Armor;
import POO.PolymorphiaRPG.src.items.Potion;
import POO.PolymorphiaRPG.src.items.Weapon;
import POO.PolymorphiaRPG.src.items.Spell;
import java.util.ArrayList;
import java.util.List;


public class Player extends Character {

    private int level;
    private int intcoins;
    private Inventory inventory;
    private Weapon weapon;
    private List<Spell> spells = new ArrayList<>();
    private Armor armor;
    private int baseDefense = 0; // défense de base


    public Player(String name) {
        super(name, 100, 10, 5);
        this.level = 1;
        this.intcoins = 100;
        this.inventory = new Inventory();
        this.defense = baseDefense;
    }

    public void attack(Character target) {
        System.out.println(name + " attaque " + target.getName());
        target.takeDamage(this.attack);
    }

    public void usePotion(Potion potion) {
        life += potion.getHeal();
        System.out.println(name + " récupère " + potion.getHeal() + " PV.");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getIntcoins() {
        return intcoins;
    }

    public void addIntcoins(int amount) {
        intcoins += amount;
    }
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.attack += weapon.getAttackBonus();
        System.out.println(name + " équipe " + weapon.getName());
    }
    public void learnSpell(Spell spell) {
        spells.add(spell);
        System.out.println(name + " apprend le sort : " + spell.getName());
    }
    public void castSpell(int index, Character target) {
    if (index < 0 || index >= spells.size()) {
        System.out.println("Sort invalide !");
        return;
        }
        Spell spell = spells.get(index);
        spell.cast(target);
    }
    public List<Spell> getSpells() {
        return spells;
    }
    public int getLevel() {
        return level;
    }
    public int getWeapon() {
        return weapon.getAttackBonus();
    }
    public void equipArmor(Armor armor) {
        if (this.armor != null) {
            // Retirer bonus de l'ancienne armure
            this.defense -= this.armor.getDefenseBonus();
        }
        this.armor = armor;
        this.defense += armor.getDefenseBonus();
        System.out.println(name + " équipe " + armor.getName() + " (Défense +"
            + armor.getDefenseBonus() + ")");
    }
    public void takeDamage(int damage) {
        int actualDamage = damage - defense;
        if (actualDamage < 0) actualDamage = 0;
        life -= actualDamage;
        System.out.println(name + " subit " + actualDamage + " dégâts (PV restants : " + life + ")");
    }
    public int getDefense() {
        return defense;
    }

}   
