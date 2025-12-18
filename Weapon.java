package POO.PolymorphiaRPG.src.items;

public class Weapon extends Item {

    private int attackBonus;

    public Weapon(String name, int price, int attackBonus) {
        super(name, price);
        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }
    public void setAttack(int attack) {
        this.attackBonus = attack;
    }

}
