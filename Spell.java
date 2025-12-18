package POO.PolymorphiaRPG.src.items;

import POO.PolymorphiaRPG.src.characters.Character;

public class Spell extends Item {

    private int damage;

    public Spell(String name, int price, int damage) {
        super(name, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void cast(Character target) {
        System.out.println("Sort " + name + " lancé sur " + target.getName());
        target.takeDamage(damage);
    }
}
