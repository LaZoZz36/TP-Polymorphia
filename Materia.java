package POO.PolymorphiaRPG.src.items;

public class Materia extends Item {

    private int bonus; // bonus à ajouter à l'arme ou à l'armure

    public Materia(String name, int price, int bonus) {
        super(name, price);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void apply(Weapon weapon) {
        weapon.setAttack(weapon.getAttackBonus() + bonus);
        System.out.println("La materia " + name + " augmente l'attaque de " + weapon.getName() + " de " + bonus);
    }

    public void apply(Armor armor) {
        armor.setDefenseBonus(armor.getDefenseBonus() + bonus);
        System.out.println("La materia " + name + " augmente la défense de " + armor.getName() + " de " + bonus);
    }
}
