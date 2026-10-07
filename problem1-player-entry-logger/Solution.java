import java.util.*;

/**
 * Problem 1: Player Entry Logger
 * Reads every non-empty line ("name score") from standard input
 * and prints them as a JSON array of {"key":["..."]} objects.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        // Read lines and add to results list
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            results.add(line);
        }

        scanner.close();

        // Print results in the required format
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < results.size(); i++) {
            sb.append("{\"key\":[\"").append(results.get(i)).append("\"]}");
            if (i != results.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
    }
}
