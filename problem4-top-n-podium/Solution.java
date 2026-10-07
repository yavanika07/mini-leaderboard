import java.util.*;

/**
 * Problem 4: The Top N Podium
 * First input line is N. Remaining lines are "name score".
 * Prints only the top N players by score.
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

        // Read N first
        int n = Integer.parseInt(scanner.nextLine().trim());

        // Read remaining lines
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(" ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            players.add(new Player(name, score));
        }

        scanner.close();

        // Sort descending (stable sort keeps original order on ties)
        players.sort((a, b) -> Integer.compare(b.score, a.score));

        // Limit to N (Math.min avoids IndexOutOfBoundsException when N > players)
        int limit = Math.min(n, players.size());
        List<Player> topPlayers = players.subList(0, limit);

        // Print using template
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < topPlayers.size(); i++) {
            Player p = topPlayers.get(i);
            sb.append("{\"key\":[\"").append(p.name).append(" ").append(p.score).append("\"]}");
            if (i != topPlayers.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.print(sb.toString());
    }
}
