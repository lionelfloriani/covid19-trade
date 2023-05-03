import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class UI extends Messages {
    private String query;
    private Scanner scanner = new Scanner(System.in);
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    public UI() {}

    public void start() {
        System.out.print(WELCOME_MENU);
        System.out.println(INSERT_QUERY);
        System.out.print("\033[1m>\033[0m ");
        query = scanner.nextLine().toLowerCase();
    }

    public void running() {
        while (!query.matches("exit")) {
            processQuery();
        }
        System.out.println(EXIT);
    }

    public void processQuery() {
        boolean helpQuery = query.contains(" ");
        boolean emptyQuery = query.isEmpty();
        if (helpQuery) {
            String[] helpCommand = query.split(" ");
            switch (helpCommand[1]) {
                case "monthly_average" -> System.out.println(HELP_M_AVERAGE);
                case "monthly_total" -> System.out.println(HELP_M_TOTAL);
                case "yearly_average" -> System.out.println(HELP_Y_AVERAGE);
                case "yearly_total" -> System.out.println(HELP_Y_TOTAL);
                case "overview" -> System.out.println(HELP_OVERVIEW);
                default -> printErrorHelp();
            }
        } else {
            switch (query) {
                case "help" -> System.out.println(MAIN_HELP);
                case "monthly_average", "ma", "monthly_total", "mt", "yearly_average", "ya", "yearly_total", "yt" -> {
                    String year = readYear();
                    switch (query) {
                        case "monthly_average", "ma", "monthly_total", "mt" -> {
                            String month = readMonth();
                            boolean wantsCustomParam = readCustomParam();
                            String country = "";
                            String commodity = "";
                            String transport = "";
                            String measurement = "";
                            if (wantsCustomParam) {
                                country = readCountry();
                                commodity = readCommodity();
                                transport = readTransportMode();
                                measurement = readMeasurement();
                            } else {
                                country = "all";
                                commodity = "all";
                                transport = "all";
                                measurement = "\\$";
                            }
                            switch (query) {
                                case "monthly_average", "ma" -> System.out.println(monthlyAverage(year, month, country, commodity,
                                        transport, measurement));
                                case "monthly_total", "mt" -> System.out.println(monthlyTotal(year, month, country, commodity,
                                        transport, measurement));
                            }

                        }
                        case "yearly_average", "ya", "yearly_total", "yt" -> {
                            boolean wantsCustomParam = readCustomParam();
                            String country = "";
                            String commodity = "";
                            String transport = "";
                            String measurement = "";
                            if (wantsCustomParam) {
                                country = readCountry();
                                commodity = readCommodity();
                                transport = readTransportMode();
                                measurement = readMeasurement();
                            } else {
                                country = "all";
                                commodity = "all";
                                transport = "all";
                                measurement = "\\$";
                            }
                            switch (query) {
                                case "yearly_average", "ya" -> System.out.println(yearlyAverage(year, country, commodity, transport,
                                        measurement));
                                case "yearly_total", "yt" -> System.out.println(yearlyTotal(year, country, commodity, transport,
                                        measurement));
                            }
                        }
                    }
                }
                case "overview", "ov" -> {
                    getOverview();
                }
                default -> printErrorCommand();
            }
        }
        if (!emptyQuery) System.out.println(INSERT_QUERY);
        System.out.print("\033[1m>\033[0m ");
        query = scanner.nextLine().toLowerCase();
    }

    private void printErrorCommand() {
        if (!query.isEmpty()) {
            System.out.printf("%s is unknown.%n", query);
        }
    }

    private void printErrorHelp() {
        String[] helpCommand = query.split(" ");
        System.out.printf("""
                "%s" is unknown.
                
                Please use "help " +
                - monthly_average
                - monthly_total
                - yearly_average
                - yearly_total
                - overview
                
                """, helpCommand[1], helpCommand[1], helpCommand[1]);
    }

    private boolean readCustomParam() {
        String input = "";
        boolean wantsCustomParam = false;
        while (true) {
            System.out.println("Do you want to use custom parameters? (Y/N)");
            System.out.print("\033[1m>\033[0m ");
            input = scanner.nextLine();
            if (input.toLowerCase().matches("y")) {
                wantsCustomParam = true;
                break;
            } else if (input.toLowerCase().matches("n")) {
                break;
            }
            System.out.printf("\"%s\" is not valid.%n", input);
            System.out.println("...");
        }
        return wantsCustomParam;
    }
    private String readMonth() {
        String month = "";
        while (true) {
            System.out.println("Month?");
            System.out.print("\033[1m>\033[0m ");
            month = scanner.nextLine().toLowerCase();
            if (month.matches("^(0[1-9]|1[0-2])$")) {
                break;
            }
            if (month.matches("^(january|february|march|april|" +
                    "mai|june|july|august|september|october|november|december)$")) {
                switch (month) {
                    case "january" -> month = "01";
                    case "february" -> month = "02";
                    case "march" -> month = "03";
                    case "april" -> month = "04";
                    case "mai" -> month = "05";
                    case "june" -> month = "06";
                    case "july" -> month = "07";
                    case "august" -> month = "08";
                    case "september" -> month = "09";
                    case "october" -> month = "10";
                    case "november" -> month = "11";
                    case "december" -> month = "12";
                }
                break;
            }
            if (month.matches("^(jan|feb|mar|apr|" +
                    "mai|jun|jul|aug|sep|oct|nov|dec)$")) {
                switch (month) {
                    case "jan" -> month = "01";
                    case "feb" -> month = "02";
                    case "mar" -> month = "03";
                    case "apr" -> month = "04";
                    case "mai" -> month = "05";
                    case "jun" -> month = "06";
                    case "jul" -> month = "07";
                    case "aug" -> month = "08";
                    case "sep" -> month = "09";
                    case "oct" -> month = "10";
                    case "nov" -> month = "11";
                    case "dec" -> month = "12";
                }
                break;
            }
            System.out.println("Invalid input.");
            System.out.println("-> '01' OR 'jan' OR 'january'");
            System.out.println("...");
        }
        return month;
    }

    private String readYear() {
        String year = "";
        while (true) {
            System.out.println("Year?");
            System.out.print("\033[1m>\033[0m ");
            year = scanner.nextLine().toLowerCase();
            if (year.matches("^(201[5-9]|202[0-2])$")) {
                break;
            }
            System.out.printf("'%s' is out of range.%n", year);
            System.out.println("-> 2015-2021");
            System.out.println("...");
        }
        return year;
    }

    private String readCountry() {
        String country = "";
        while (true) {
            System.out.println("Country?");
            Object[] countryValues = ReadCsv.getUniqueValuesByTag().get("country").toArray();
            System.out.println("-".repeat(25));
            for (int i = 0; i < countryValues.length; i++) {
                System.out.printf("%d) %s%n", i + 1, countryValues[i]);
            }
            System.out.println("-".repeat(25));
            System.out.print("\033[1m>\033[0m ");
            country = scanner.nextLine().toLowerCase();
            if (country.matches("^(australia|european union|japan|all|china|east asia|" +
                    "united states|total|united kingdom)$")) {
                break;
            } else if (country.matches("[1-9]")) {
                switch (country) {
                    case "1" -> country = "all";
                    case "2" -> country = "australia";
                    case "3" -> country = "china";
                    case "4" -> country = "east asia";
                    case "5" -> country = "european union";
                    case "6" -> country = "japan";
                    case "7" -> country = "total";
                    case "8" -> country = "united kingdom";
                    case "9" -> country = "united states";
                }
                break;
            } else if (country.isEmpty()) {
                country = "all";
                break;
            }
            System.out.println("Incorrect value.");
        }
        return country;
    }

    private String readCommodity() {
        String commodity = "";
        while (true) {
            System.out.println("Commodity?");
            Object[] commodityValues = ReadCsv.getUniqueValuesByTag().get("commodity").toArray();
            System.out.println("-".repeat(25));
            for (int i = 0; i < commodityValues.length; i++) {
                System.out.printf("%d) %s%n", i + 1, commodityValues[i]);
            }
            System.out.println("-".repeat(25));
            System.out.print("\033[1m>\033[0m ");
            commodity = scanner.nextLine().toLowerCase();
            if (commodity.matches("^(electrical machinery|fish, crustaceans, molluscs|fruit|" +
                    "logs, wood, wood articles|milk powder, butter, cheese|non-food manufactured goods|all|mechanical machinery|meat)$")) {
                break;
            } else if (commodity.matches("[1-9]")) {
                switch (commodity) {
                    case "1" -> commodity = "all";
                    case "2" -> commodity = "electrical machinery";
                    case "3" -> commodity = "fish, crustaceans, molluscs";
                    case "4" -> commodity = "fruit";
                    case "5" -> commodity = "logs, wood, wood articles";
                    case "6" -> commodity = "meat";
                    case "7" -> commodity = "mechanical machinery";
                    case "8" -> commodity = "milk powder, butter, cheese";
                    case "9" -> commodity = "non-food manufactured goods";
                }
                break;
            } else if (commodity.isEmpty()) {
                commodity = "all";
                break;
            }
            System.out.println("Incorrect value.");
        }
        return commodity;
    }

    private String readTransportMode() {
        String transport = "";
        while (true) {
            System.out.println("Transport Mode?");
            Object[] transportValues = ReadCsv.getUniqueValuesByTag().get("transport_mode").toArray();
            System.out.println("-".repeat(25));
            for (int i = 0; i < transportValues.length; i++) {
                System.out.printf("%d) %s%n", i + 1, transportValues[i]);
            }
            System.out.println("-".repeat(25));
            System.out.print("\033[1m>\033[0m ");
            transport = scanner.nextLine().toLowerCase();
            if (transport.matches("^(air|all|sea)$")) {
                break;
            } else if (transport.matches("[1-3]")) {
                switch (transport) {
                    case "1" -> transport = "air";
                    case "2" -> transport = "all";
                    case "3" -> transport = "sea";
                }
                break;
            } else if (transport.isEmpty()) {
                transport = "all";
                break;
            }
            System.out.println("Incorrect value.");
            System.out.println();
            System.out.println("Available values :");
            System.out.println(TRANSPORT_VALUE);
        }
        return transport;
    }

    private String readMeasurement() {
        String measurement = "";
        while (true) {
            System.out.println("Measurement?");
            Object[] measurementValues = ReadCsv.getUniqueValuesByTag().get("measure").toArray();
            System.out.println("-".repeat(25));
            for (int i = 0; i < measurementValues.length; i++) {
                System.out.printf("%d) %s%n", i + 1, measurementValues[i]);
            }
            System.out.println("-".repeat(25));
            System.out.print("\033[1m>\033[0m ");
            measurement = scanner.nextLine();
            if (measurement.equals("$")) {
                measurement = "\\$";
                break;
            } else if (measurement.matches("[1-2]")) {
                switch (measurement) {
                    case "1" -> measurement = "\\$";
                    case "2" -> measurement = "tonnes";
                }
                break;
            } else if (measurement.equals("tonnes")) {
                break;
            } else if (measurement.isEmpty()) {
                measurement = "\\$";
                break;
            }
            System.out.println("Incorrect value.");
        }
        return measurement;
    }

    private String monthlyAverage(String year, String month, String country, String commodity, String transport, String measurement) {
        List<Line> selectedRange = filterToList(year, month, country, commodity, transport, measurement);
        Double sum = 0.0;
        int dividedBy = 0;
        for (var e : selectedRange) {
            sum += Double.parseDouble(e.getValue());
            dividedBy++;
        }
        return OUTPUT_M_AVERAGE.formatted(month ,year, defaultParamOutput(country), defaultParamOutput(commodity),
                defaultParamOutput(transport),
                defaultParamOutput(measurement), decimalFormat.format(sum / dividedBy));
    }

    private String monthlyTotal(String year, String month, String country, String commodity, String transport, String measurement) {
        List<Line> selectedRange = filterToList(year, month, country, commodity, transport, measurement);
        Double sum = 0.0;
        for (var e : selectedRange) {
            sum += Double.parseDouble(e.getValue());
        }
        return OUTPUT_M_TOTAL.formatted(month, year, defaultParamOutput(country), defaultParamOutput(commodity),
                defaultParamOutput(transport),
                defaultParamOutput(measurement), decimalFormat.format(sum));
    }

    private String yearlyAverage(String year, String country, String commodity, String transport, String measurement) {
        List<Line> selectedRange = filterToListYear(year, country, commodity, transport, measurement);
        double sum = 0.0;
        int dividedBy = 0;
        for (var e : selectedRange) {
            sum += Double.parseDouble((e.getValue()));
            dividedBy++;
        }
        return OUTPUT_Y_AVERAGE.formatted(year, defaultParamOutput(country), defaultParamOutput(commodity),
                defaultParamOutput(transport),
                defaultParamOutput(measurement), decimalFormat.format(sum / dividedBy));
    }

    private String yearlyTotal(String year, String country, String commodity, String transport, String measurement) {
        List<Line> selectedRange = filterToListYear(year, country, commodity, transport, measurement);
        double sum = 0.0;
        int dividedBy = 0;
        for (var e : selectedRange) {
            sum += Double.parseDouble((e.getValue()));
            dividedBy++;
        }
        return OUTPUT_Y_TOTAL.formatted(year, defaultParamOutput(country), defaultParamOutput(commodity),
                defaultParamOutput(transport),
                defaultParamOutput(measurement), decimalFormat.format(sum));
    }

    private List<Line> filterToList(String year, String month, String country, String commodity, String transport,
                                   String measurement) {
        return ReadCsv.getData().stream().filter(line ->
                (line.getDirection().matches("imports") || line.getDirection().matches("exports")) &&
                        line.getYear().matches(year) && line.getMonth().matches(month) && line.getCountry().matches(country)
                        && line.getCommodity().matches(commodity) && line.getTransportMode().matches(transport) &&
                        line.getMeasure().matches(measurement)
        ).toList();
    }

    private List<Line> filterToListYear(String year, String country, String commodity, String transport,
                                    String measurement) {
        return ReadCsv.getData().stream().filter(line ->
                (line.getDirection().matches("imports") || line.getDirection().matches("exports")) &&
                        line.getYear().matches(year) && line.getCountry().matches(country)
                        && line.getCommodity().matches(commodity) && line.getTransportMode().matches(transport) &&
                        line.getMeasure().matches(measurement)
        ).toList();
    }

    private void getOverview() {
        System.out.println();
        for (var e : ReadCsv.getUniqueValuesByTag().entrySet()) {
            if (e.getKey().matches("year")) {
                System.out.printf("Values for \033[1m%s\033[0m :%n", e.getKey());
                System.out.println("-".repeat(10));
                for (int i = 0; i < ReadCsv.getUniqueValuesByTag().get("year").toArray().length; i++) {
                    if (i != ReadCsv.getUniqueValuesByTag().get("year").toArray().length - 1) {
                        System.out.printf("%d - %s%n", i + 1, ReadCsv.getUniqueValuesByTag().get("year").toArray()[i]);
                    } else {
                        System.out.printf("%d - %s %s%n", i + 1, ReadCsv.getUniqueValuesByTag().get("year").toArray()[i], ITALIC_MONTH_MAX);
                    }
                }
                System.out.println();
            } else {
                System.out.printf("Values for \033[1m%s\033[0m :%n", e.getKey());
                System.out.println("-".repeat(10));
                for (int i = 0; i < e.getValue().toArray().length; i++) {
                    if (e.getValue().toArray()[i].equals("all") ||
                            e.getValue().toArray()[i].equals("$")) {
                        System.out.printf("%d - %s %s%n", i + 1, e.getValue().toArray()[i], ITALIC_DEFAULT);
                    } else {
                        System.out.printf("%d - %s%n", i + 1, e.getValue().toArray()[i]);
                    }
                }
                System.out.println();
            }
        }
    }

    private String defaultParamOutput(String param) {
        if (param.matches("all")) {
            return "\033[3m%s\033[0m".formatted(param);
        } else if (param.equals("\\$")) {
            return "\u001B[3m$\u001B[0m";
        } else {
            return param;
        }
    }
}
