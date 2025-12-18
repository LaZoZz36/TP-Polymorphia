package POO.PolymorphiaRPG.src.game;

import POO.PolymorphiaRPG.src.characters.*;
import java.util.Random;

public class MonsterFactory {

    public static Monster randomMonster() {
        Random rand = new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                return new Zombie();
            case 1:
                return new Wolf();
            default:
                return new Dragon();
        }
    }
}
