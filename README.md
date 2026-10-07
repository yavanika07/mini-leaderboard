# Mini Leaderboard System

A Java leaderboard pipeline built during the **AICTE – EduSkills Java Full Stack Virtual Internship** (Jun – Jul 2026, 8 weeks).

The system ingests player scores, keeps each player's highest score, ranks players in descending order, returns the Top-N, and exports the result as valid JSON with special characters escaped.

## Pipeline

```
Input (name score) ──► 1. Entry Logger ──► 2. High Score Filter ──► 3. Ranked Leaderboard ──► 4. Top N Podium ──► 5. JSON Exporter ──► JSON output
```

## Project structure

| Folder | Problem | Key concept |
|---|---|---|
| `problem1-player-entry-logger/` | Read and log player entries | `Scanner`, `ArrayList`, `StringBuilder` |
| `problem2-high-score-filter/` | Keep only each player's highest score | `LinkedHashMap` (average O(1) lookup) |
| `problem3-ranked-leaderboard/` | Sort players by score, descending | Custom `Comparator`, stable sort |
| `problem4-top-n-podium/` | Return only the top N players | `subList()`, `Math.min()` bound check |
| `problem5-json-exporter/` | Export valid JSON | Escape `\` first, then `"` |
| `full-pipeline/` | All five steps combined in one program | `Map.merge()`, comparator, `subList()`, escaping |

Each folder contains the source code, an `input.txt`, and the `expected_output.txt` produced by running it.

## Input / output format

**Input:** one player per line as `name score`. Problem 4 and the full pipeline read **N** on the first line.

**Output:** a JSON array of objects:
```json
[{"key":["Priya 720"]},{"key":["Sneha 720"]},{"key":["Arjun 680"]}]
```

## How to run

Requires Java 17 or newer. Open a terminal inside any problem folder:

```bash
javac Solution.java
java Solution < input.txt
```

For the full pipeline:
```bash
cd full-pipeline
javac Leaderboard.java
java Leaderboard < input.txt
```

> **Windows PowerShell** does not support `<`. Use this instead:
> `Get-Content input.txt | java Solution`

## Example (full pipeline)

Input:
```
3
Arjun 450
Priya 720
Ravi 610
Arjun 680
Sneha 720
"The Pro" 590
C:\gamer 400
Priya 300
Kiran abc
```

Output:
```json
[{"key":["Priya 720"]},{"key":["Sneha 720"]},{"key":["Arjun 680"]}]
```

- Arjun's duplicate entry keeps only 680, and Priya's keeps 720
- Priya and Sneha tie at 720 and keep their input order (stable sort)
- `Kiran abc` is skipped because the score is not a number
- Names with quotes or backslashes, like `"The Pro"` and `C:\gamer`, are escaped in the JSON

## Challenges and solutions

| Challenge | Solution |
|---|---|
| Duplicate player entries | HashMap with the name as key; store a new score only if it is higher |
| Special characters breaking JSON | Escape backslashes first, then double quotes |
| N larger than the number of players | `Math.min(n, players.size())` before `subList()` |
| Predictable order for tied scores | Stable sort keeps the original input order |

## Future scope
- Save and load leaderboard data from CSV/JSON files or a database
- Live score updates that re-rank players dynamically
- Multi-level tie-breaking rules
- A simple GUI or web front end

## Tech
Java, Collections Framework (`HashMap`, `LinkedHashMap`, `ArrayList`, `Comparator`), `Scanner`, `StringBuilder`
