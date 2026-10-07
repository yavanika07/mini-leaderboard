import java.util.*;

/**
 * Problem 2: High Score Filter
 * Keeps only the highest score for each player.
 * LinkedHashMap keeps players in the order they first appeared.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> scores = new LinkedHashMap<>();
        List<String> results = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(" ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);

            // Store the score if the player is new, or if it beats their old score
            if (!scores.containsKey(name) || score > scores.get(name)) {
                scores.put(name, score);
            }
        }

        scanner.close();

        // Prepare results list from map
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            results.add(entry.getKey() + " " + entry.getValue());
        }

        // Final printing logic
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < results.size(); i++) {
            sb.append("{\"key\":[\"").append(results.get(i)).append("\"]}");
            if (i != results.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.print(sb.toString());
    }
}
