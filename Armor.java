package POO.PolymorphiaRPG.src.items;

public class Armor extends Item {

    private int defenseBonus;

    public Armor(String name, int price, int defenseBonus) {
        super(name, price);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }

}
