package POO.PolymorphiaRPG.src.characters;

public class Monster extends Character {

    protected int rewardCoins;

    public Monster(String name, int life, int attack, int defense, int rewardCoins) {
        super(name, life, attack, defense);
        this.rewardCoins = rewardCoins;
    }

    public int getRewardCoins() {
        return rewardCoins;
    }

    public void attack(Player player) {
        System.out.println(name + " attaque " + player.getName());
        player.takeDamage(this.attack);
    }
}
