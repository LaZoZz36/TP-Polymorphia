package POO.PolymorphiaRPG.src.game;

import POO.PolymorphiaRPG.src.characters.Monster;
import POO.PolymorphiaRPG.src.characters.Player;
import POO.PolymorphiaRPG.src.items.Armor;
import POO.PolymorphiaRPG.src.items.Potion;
import POO.PolymorphiaRPG.src.items.Spell;
import POO.PolymorphiaRPG.src.items.Weapon;
import POO.PolymorphiaRPG.src.shop.Merchant;
import POO.PolymorphiaRPG.src.items.Materia;

import java.util.Scanner;

public class Menu {

    private Player player;
    private Merchant merchant;
    private Scanner scanner;

    public Menu(Player player, Merchant merchant) {
        this.player = player;
        this.merchant = merchant;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== " + player.getName() + " ===");
            System.out.println("PV : " + player.getLife() + " | Intcoins : " + player.getIntcoins()
                + " | Défense : " + player.getDefense());

            System.out.println("\n=== Menu ===");
            System.out.println("1 - Acheter chez le marchand"); 
            System.out.println("2 - Explorer Polymorphia");
            System.out.println("3 - S'équiper d'une arme");
            System.out.println("4 - Utiliser une potion");
            System.out.println("5 - Lancer un sort");
            System.out.println("6 - S'équiper d'une armure");
            System.out.println("7 - Utiliser une Materia");
            System.out.println("0 - Quitter");
            System.out.print("Choix : ");

            if (!player.isAlive()) {
                System.out.println("\n Javalt est mort ! Le jeu est terminé...");
                break; // quitte la boucle et donc le jeu
            }


            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne

            switch (choice) {
                case 1:
                    buy();
                    break;
                case 2:
                    explore();
                    break;
                case 3:
                    equipWeapon();
                    break;
                case 4:
                    usePotion();
                    break;
                case 5:
                    castSpell();
                    break;
                case 6:
                    equipArmor();
                    break;
                case 7:
                    useMateria();
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void buy() {
        merchant.showStock(player);
        System.out.print("Sélectionnez l'objet à acheter (numéro) : ");
        int index = scanner.nextInt();
        scanner.nextLine();
        merchant.sell(player, index);
    }

    private void explore() {
        System.out.println("Vous explorez Polymorphia...");
        Monster monster = MonsterFactory.randomMonster();
        System.out.println("Un " + monster.getName() + " apparaît !");
        fight(monster);
    }

    private void equipWeapon() {
        System.out.println("Inventaire :");
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i) instanceof Weapon) {
                System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
            }
        }
        System.out.print("Numéro de l'arme à équiper : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (player.getInventory().getItems().get(index) instanceof Weapon) {
            Weapon weapon = (Weapon) player.getInventory().getItems().get(index);
            player.equipWeapon(weapon);
        } else {
            System.out.println("Ce n'est pas une arme !");
        }
    }

    private void usePotion() {
        // Filtrer les potions dans l'inventaire
        boolean hasPotion = false;
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i) instanceof Potion) {
                System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
                hasPotion = true;
            }
        }

        if (!hasPotion) {
            System.out.println("Aucune potion disponible !");
            return;
        }

        System.out.print("Numéro de la potion à utiliser : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        // Vérifier que c'est bien une potion
        if (index < 0 || index >= player.getInventory().getItems().size() ||
            !(player.getInventory().getItems().get(index) instanceof Potion)) {
            System.out.println("Choix invalide !");
            return;
        }

        // Utiliser la potion
        Potion potion = (Potion) player.getInventory().getItems().get(index);
        player.usePotion(potion);

        // Supprimer la potion après usage
        player.getInventory().getItems().remove(index);
    }


    private void fight(Monster monster) {
        while (player.isAlive() && monster.isAlive()) {
            player.attack(monster);
            if (monster.isAlive()) {
                monster.attack(player);
            }
        }

        if (player.isAlive()) {
            System.out.println(monster.getName() + " vaincu !");
            player.addIntcoins(monster.getRewardCoins());
            System.out.println("Récompense : " + monster.getRewardCoins() + " intcoins");
        } else {
            System.out.println("Javalt est mort...");
        }
    }
    private void castSpell() {
        if (player.getSpells().isEmpty()) {
            System.out.println("Aucun sort appris !");
            return;
        }

        System.out.println("Sorts disponibles :");
        for (int i = 0; i < player.getSpells().size(); i++) {
            System.out.println(i + " - " + player.getSpells().get(i).getName() +
                " (dégâts : " + player.getSpells().get(i).getDamage() + ")");
        }

        System.out.print("Numéro du sort à lancer : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        // Vérification de l'index
        if (index < 0 || index >= player.getSpells().size()) {
            System.out.println("Choix invalide !");
            return;
        }

        // Créer un monstre pour le combat
        Monster monster = MonsterFactory.randomMonster();
        System.out.println("Un " + monster.getName() + " apparaît !");

        // Lancer le sort
        Spell spell = player.getSpells().get(index);
        spell.cast(monster);

        // Supprimer le sort après usage si usage unique
        player.getSpells().remove(index);
        player.getInventory().getItems().remove(spell);

        // Combat si le monstre est toujours vivant
        if (monster.isAlive()) {
            monster.attack(player);
        } else {
            System.out.println(monster.getName() + " vaincu !");
            player.addIntcoins(monster.getRewardCoins());
            System.out.println("Récompense : " + monster.getRewardCoins() + " intcoins");
        }
    }
    private void equipArmor() {
        System.out.println("Inventaire :");
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i) instanceof Armor) {
                System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
            }
        }

        System.out.print("Numéro de l'armure à équiper : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (player.getInventory().getItems().get(index) instanceof Armor) {
            Armor armor = (Armor) player.getInventory().getItems().get(index);
            player.equipArmor(armor);
        } else {
            System.out.println("Ce n'est pas une armure !");
    }
}
    private void useMateria() {
        // Afficher toutes les materias dans l’inventaire
        boolean hasMateria = false;
        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItems().get(i) instanceof Materia) {
                System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
                hasMateria = true;
            }
        }

        if (!hasMateria) {
            System.out.println("Aucune Materia disponible !");
            return;
        }

        System.out.print("Numéro de la Materia à utiliser : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= player.getInventory().getItems().size() ||
            !(player.getInventory().getItems().get(index) instanceof Materia)) {
            System.out.println("Choix invalide !");
            return;
        }

        Materia materia = (Materia) player.getInventory().getItems().get(index);

        // Demander si le joueur veut l’appliquer sur une arme ou une armure
        System.out.println("1 - Améliorer une arme");
        System.out.println("2 - Améliorer une armure");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            // Afficher les armes
            for (int i = 0; i < player.getInventory().getItems().size(); i++) {
                if (player.getInventory().getItems().get(i) instanceof Weapon) {
                    System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
                }
            }
            System.out.print("Numéro de l’arme : ");
            int weaponIndex = scanner.nextInt();
            scanner.nextLine();
            if (player.getInventory().getItems().get(weaponIndex) instanceof Weapon) {
                Weapon weapon = (Weapon) player.getInventory().getItems().get(weaponIndex);
                materia.apply(weapon);
                player.getInventory().getItems().remove(materia); // supprimer la materia
            } else {
                System.out.println("Choix invalide !");
            }
        } else if (choice == 2) {
            // Afficher les armures
            for (int i = 0; i < player.getInventory().getItems().size(); i++) {
                if (player.getInventory().getItems().get(i) instanceof Armor) {
                    System.out.println(i + " - " + player.getInventory().getItems().get(i).getName());
                }
            }
            System.out.print("Numéro de l’armure : ");
            int armorIndex = scanner.nextInt();
            scanner.nextLine();
            if (player.getInventory().getItems().get(armorIndex) instanceof Armor) {
                Armor armor = (Armor) player.getInventory().getItems().get(armorIndex);
                materia.apply(armor);
                player.getInventory().getItems().remove(materia); // supprimer la materia
            } else {
                System.out.println("Choix invalide !");
            }
        } else {
            System.out.println("Choix invalide !");
        }
    }

}
