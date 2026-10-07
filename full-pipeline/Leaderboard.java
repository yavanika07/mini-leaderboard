import java.util.*;

/**
 * Full Pipeline: combines Problems 1-5 into one program.
 *
 *   Input  (standard input):
 *     line 1      : N  (how many top players to show)
 *     other lines : name score
 *
 *   Steps:
 *     1. Log entries      - read and parse every "name score" line
 *     2. High score filter - keep each player's highest score (HashMap, O(1) average per lookup)
 *     3. Rank             - sort by score, descending (stable custom comparator)
 *     4. Top N            - subList(0, min(N, size))
 *     5. Export JSON      - escape backslashes and quotes so output always parses
 *
 *   Output format:
 *     [{"key":["name score"]},{"key":["name score"]},...]
 */
public class Leaderboard {

    static class Player {
        final String name;
        final int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    // Highest score first. List.sort() is stable, so equal scores keep the
    // order in which the players first appeared in the input.
    static final Comparator<Player> BY_SCORE_DESC =
            (a, b) -> Integer.compare(b.score, a.score);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) {
            System.out.print("[]");
            return;
        }

        // ---- Read N ----
        int n = Integer.parseInt(scanner.nextLine().trim());

        // ---- Step 1 + 2: log entries and keep each player's highest score ----
        // LinkedHashMap = HashMap that also remembers insertion order,
        // which keeps tie-breaking predictable.
        Map<String, Integer> bestScores = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            // Split on the LAST space, so names containing spaces still work.
            int space = line.lastIndexOf(' ');
            if (space <= 0) continue;                    // malformed line, skip

            String name = line.substring(0, space).trim();
            int score;
            try {
                score = Integer.parseInt(line.substring(space + 1).trim());
            } catch (NumberFormatException e) {
                continue;                                // non-numeric score, skip
            }

            // merge: new player -> store score; existing player -> keep the max
            bestScores.merge(name, score, Math::max);
        }
        scanner.close();

        // ---- Step 3: rank ----
        List<Player> players = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : bestScores.entrySet()) {
            players.add(new Player(entry.getKey(), entry.getValue()));
        }
        players.sort(BY_SCORE_DESC);

        // ---- Step 4: top N ----
        int limit = Math.max(0, Math.min(n, players.size()));
        List<Player> topPlayers = players.subList(0, limit);

        // ---- Step 5: export JSON ----
        System.out.print(toJson(topPlayers));
    }

    static String toJson(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            sb.append("{\"key\":[\"")
              .append(escape(p.name + " " + p.score))
              .append("\"]}");
            if (i != players.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /** Escape backslashes FIRST, then quotes. */
    static String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
