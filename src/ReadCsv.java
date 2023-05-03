import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadCsv {
    private File file = new File("/Users/lionelfloriani/Code/Java/covid19-trade/data/covid.txt");
    private Scanner scanner = new Scanner(file);
    private static List<Line> data = new ArrayList<>();
    private static Map<String, Set<String>> uniqueValuesByTag = new HashMap<>();


    public ReadCsv() throws FileNotFoundException {
        String[] tagLine = scanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        while (scanner.hasNext()) {
            data.add(new Line(scanner.nextLine().toLowerCase()));
        }

        for (int i = 0; i < tagLine.length; i++) {
            String currentTag = tagLine[i].toLowerCase();
            String[] currentColumn = new String[data.size()];
            for (int j = 0; j < data.size(); j++) {
                String currentField = data.get(j).getField(i);
                if (currentField.startsWith("\"") && currentField.endsWith("\"")) {
                    currentField = currentField.substring(1, currentField.length() - 1);
                    switch (currentField) {
                        case "east asia (excluding china)" -> currentField = "east asia";
                        case "european union (27)" -> currentField = "european union";
                        case "total (excluding china)" -> currentField = "total";
                        case "electrical machinery and equip" -> currentField = "electrical machinery";
                        case "fish, crustaceans, and molluscs" -> currentField = "fish, crustaceans, molluscs";
                        case "logs, wood, and wood articles" -> currentField = "logs, wood, wood articles";
                        case "meat and edible offal" -> currentField = "meat";
                        case "mechanical machinery and equip" -> currentField = "mechanical machinery";
                        case "milk powder, butter, and cheese" -> currentField = "milk powder, butter, cheese";
                    }
                }
                currentColumn[j] = currentField.toLowerCase();
            }
            Set<String> valuesSet = new HashSet<>(Arrays.asList(currentColumn));
            Set<String> sortedValuesSet = new TreeSet<>(valuesSet);
            uniqueValuesByTag.put(currentTag, sortedValuesSet);
        }

        uniqueValuesByTag.remove("weekday");
        uniqueValuesByTag.remove("cumulative");
        uniqueValuesByTag.remove("direction");
        uniqueValuesByTag.remove("date");
        uniqueValuesByTag.remove("cumulative");
        uniqueValuesByTag.remove("value");
    }

    public static List<Line> getData() {
        return data;
    }

    public static Map<String, Set<String>> getUniqueValuesByTag() {
        return uniqueValuesByTag;
    }
}
