package POO.PolymorphiaRPG.src.characters;

public abstract class Character {

    protected String name;
    protected int life;
    protected int attack;
    protected int defense;

    public Character(String name, int life, int attack, int defense) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return life > 0;
    }

    public void takeDamage(int damage) {
        int realDamage = Math.max(0, damage - defense);
        life -= realDamage;
        System.out.println(name + " perd " + realDamage + " PV.");
    }

    public int getLife() {
        return life;
    }

    public String getName() {
        return name;
    }
    public int getAttack() {
        return attack;
    }

}
