package POO.PolymorphiaRPG.src.game;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.json.JSONObject;

import POO.PolymorphiaRPG.src.characters.Player;
import POO.PolymorphiaRPG.src.items.Weapon;
import POO.PolymorphiaRPG.src.items.Armor;

public class PvPGame {
    private Scanner scanner = new Scanner(System.in);

    private Player player1;
    private Player player2;

    public PvPGame(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    // Méthode pour envoyer action au serveur
    private JSONObject sendAction(String player, String action, int attack, int defense, int value) throws IOException {
        URL url = new URL("http://localhost/server/server.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        String data = "player=" + player + "&action=" + action +
                      "&attack=" + attack + "&defense=" + defense + "&value=" + value;

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) content.append(line);
        in.close();
        conn.disconnect();

        return new JSONObject(content.toString());
    }

    // Tour par tour PvP
    public void startPvP() throws IOException {
        Player current = player1;
        Player opponent = player2;
        String currentPlayer = "player1";
        String opponentPlayer = "player2";

        System.out.println("=== Début du PvP ===");

        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\nTour de " + current.getName());
            System.out.println("PV : " + current.getLife() + " | Défense : " + current.getDefense());
            System.out.println("1 - Attaquer | 2 - Lancer un sort | 3 - Utiliser une potion");

            int choice = scanner.nextInt();
            scanner.nextLine();
            int attack = current.getAttack();
            int defense = current.getDefense();
            int value = 0;
            String action = "";

            switch (choice) {
                case 1: action="attack"; break;
                case 2: action="spell"; attack += 20; break; // sort plus fort
                case 3: 
                    action="potion";
                    System.out.print("Quelle potion utiliser ? ");
                    value = 20; // à remplacer par choix réel
                    current.usePotion(current.getInventory().getItems().get(0)); // exemple rapide
                    break;
                default: System.out.println("Choix invalide"); continue;
            }

            JSONObject result = sendAction(currentPlayer, action, attack, defense, value);
            System.out.println(result.getString("result"));

            // Mettre à jour PV du joueur adverse
            opponent.setLife(result.getInt("life"));

            if (opponent.getLife() <= 0) {
                System.out.println("\n💀 " + opponent.getName() + " est mort ! " + current.getName() + " a gagné !");
                break;
            }

            // Alterner les joueurs
            Player temp = current; current = opponent; opponent = temp;
            String tempName = currentPlayer; currentPlayer = opponentPlayer; opponentPlayer = tempName;
        }
    }

    public static void main(String[] args) throws IOException {
        Player p1 = new Player("Joueur 1");
        Player p2 = new Player("Joueur 2");

        PvPGame game = new PvPGame(p1, p2);
        game.startPvP();
    }
}
