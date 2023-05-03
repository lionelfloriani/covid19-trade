public class Line {
    private String direction;
    private String year;
    private String month;
    private String weekday;
    private String country;
    private String commodity;
    private String transportMode;
    private String measure;
    private String value;
    private String cumulative;

    public Line(String line) {
        String[] singleValues = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        direction = singleValues[0];
        year = singleValues[1];
        month = singleValues[2].substring(3, 5);
        weekday = singleValues[3];
        switch (singleValues[4]) {
            case "east asia (excluding china)" -> country = "east asia";
            case "european union (27)" -> country = "european union";
            case "total (excluding china)" -> country = "total";
            default -> country = singleValues[4];
        }
        switch (singleValues[5]) {
            case "electrical machinery and equip" -> commodity = "electrical machinery";
            case "fish, crustaceans, and molluscs" -> commodity = "fish, crustaceans, molluscs";
            case "logs, wood, and wood articles" -> commodity = "logs, wood, wood articles";
            case "meat and edible offal" -> commodity = "meat";
            case "mechanical machinery and equip" -> commodity = "mechanical machinery";
            case "milk powder, butter, and cheese" -> commodity = "milk powder, butter, cheese";
            default -> commodity = singleValues[5];
        }
        transportMode = singleValues[6];
        measure = singleValues[7];
        value = singleValues[8];
        cumulative = singleValues[9];
    }

    public String getField(int index) {
        String output = "";
        switch (index) {
            case 0 -> output = getDirection();
            case 1 -> output = getYear();
            case 2 -> output = getMonth();
            case 3 -> output = getWeekday();
            case 4 -> output = getCountry();
            case 5 -> output = getCommodity();
            case 6 -> output = getTransportMode();
            case 7 -> output = getMeasure();
            case 8 -> output = getValue();
            case 9 -> output = getCumulative();
            default -> output = "ERROR";
        }
        return output;
    }

    public String getDirection() {
        return direction;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getCountry() {
        return country;
    }

    public String getCommodity() {
        return commodity;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public String getMeasure() {
        return measure;
    }

    public String getValue() {
        return value;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getCumulative() {
        return cumulative;
    }

    @Override
    public String toString() {
        return "Line{" +
                "direction='" + direction + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", weekday='" + weekday + '\'' +
                ", country='" + country + '\'' +
                ", commodity='" + commodity + '\'' +
                ", transportMode='" + transportMode + '\'' +
                ", measure='" + measure + '\'' +
                ", value='" + value + '\'' +
                ", cumulative='" + cumulative + '\'' +
                '}';
    }
}
