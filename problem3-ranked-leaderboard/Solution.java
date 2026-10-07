import java.util.*;

/**
 * Problem 3: Ranked Leaderboard
 * Sorts all players by score in descending order.
 * List.sort() is a stable sort, so players with equal scores keep their input order.
 */
public class Solution {
    static class Player {
        String name;
        int score;
        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        // Read inputs into a custom object list
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(" ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            players.add(new Player(name, score));
        }

        scanner.close();

        // Sort descending (stable, so ties keep original order)
        players.sort((a, b) -> Integer.compare(b.score, a.score));

        // Print using the template
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            sb.append("{\"key\":[\"").append(p.name).append(" ").append(p.score).append("\"]}");
            if (i != players.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.print(sb.toString());
    }
}
