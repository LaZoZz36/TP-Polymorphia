package POO.PolymorphiaRPG.src.items;

public class Potion extends Item {

    private int heal;

    public Potion(String name, int price, int heal) {
        super(name, price);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }
}
