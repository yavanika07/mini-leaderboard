import java.util.*;

/**
 * Problem 5: Leaderboard JSON Exporter
 * Escapes special characters so the output is always valid JSON.
 * Backslashes are escaped FIRST; otherwise the backslashes added
 * for quotes would get escaped a second time.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;

            // Escape backslashes first, then quotes
            String escaped = line.replace("\\", "\\\\").replace("\"", "\\\"");
            results.add("{\"key\":[\"" + escaped + "\"]}");
        }

        scanner.close();

        // Printing logic
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < results.size(); i++) {
            sb.append(results.get(i));
            if (i != results.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.print(sb.toString());
    }
}
