public class Messages {
    protected final String WELCOME_MENU = """
            
              ------------------------------
             | \033[1mEFFECT OF COVID19 ON TRADE\033[0m  |
              ------------------------------
            
            Data analysis commandline tool on how Covid19 has affected trade as of July 2021.
            
            """;
    protected final String INSERT_QUERY = """
            ***
            Type your query to continue.
            ("help" for available commands)""";
    protected final String MAIN_HELP = """
            
            \033[1mAvailable commands\033[0m
            ------------------
            help <command>
            - Display an extended explanation of the <command>.
            
            monthly_average
            - Returns the average trade value of a specified month.
            
            monthly_total
            - Returns the total trade value of a specified month.
            
            yearly_average
            - Returns the average trade value of a specified year.
            
            yearly_total
            - Returns the total trade value of a specified year.
            
            overview
            - Returns a list of available custom parameters for queries.
            
            exit
            - Quit the program.
            """;
    protected final String HELP_M_AVERAGE = """
            
            The "\033[1mmonthly_average\033[0m" command returns the average of imports/exports of a specified month of a specified year.
            (Range : 01/2015 - 07/2021)
            ------
            4 additional optional parameters are available to use with this command :
            - country \033[3m(default: all)\033[0m
            - commodity \033[3m(default: all)\033[0m
            - transport_mode \033[3m(default: all)\033[0m
            - measure \033[3m(default: $)\033[0m
            
            The command "\033[1moverview\033[0m" can be used to retrieve the different usable values for those parameters.
            """;
    protected final String HELP_M_TOTAL = """
            
            The "\033[1mmonthly_total\033[0m" command returns the total of imports/exports of a specified month of a specified year.
            (Range : 01/2015 - 07/2021)
            ------
            4 additional optional parameters are available to use with this command :
            - country \033[3m(default: all)\033[0m
            - commodity \033[3m(default: all)\033[0m
            - transport_mode \033[3m(default: all)\033[0m
            - measure \033[3m(default: $)\033[0m
            
            The command "\033[1moverview\033[0m" can be used to retrieve the different usable values for those parameters.
            """;
    protected final String HELP_Y_AVERAGE = """
            
            The "\033[1myearly_average\033[0m" command returns the average of imports/exports of a specified year.
            (Range : 2015 - 2021)
            ------
            4 additional optional parameters are available to use with this command :
            - country \033[3m(default: all)\033[0m
            - commodity \033[3m(default: all)\033[0m
            - transport_mode \033[3m(default: all)\033[0m
            - measure \033[3m(default: $)\033[0m
            
            The command "\033[1moverview\033[0m" can be used to retrieve the different usable values for those parameters.
            """;
    protected final String HELP_Y_TOTAL = """
            
            The "\033[1myearly_average\033[0m" command returns the total of imports/exports of a specified year.
            (Range : 2015 - 2021)
            ------
            4 additional optional parameters are available to use with this command :
            - country \033[3m(default: all)\033[0m
            - commodity \033[3m(default: all)\033[0m
            - transport_mode \033[3m(default: all)\033[0m
            - measure \033[3m(default: $)\033[0m
            
            The command "\033[1moverview\033[0m" can be used to retrieve the different usable values for those parameters.
            """;
    protected String HELP_OVERVIEW = """
            
            The "\033[1moverview\033[0m" command returns a list of available values for the 4 additional parameters.
            -----------------
            When you enter your query, you will be prompted for the year wanted + the month if needed.
            You can choose up to 4 custom parameters after the two mandatory ones.
            Simply press "\033[1mENTER\033[0m" to use default values.
            """;
    protected final String EXIT = "Thanks for using our tool!\n" + "See you next time.";

    protected final String COUNTRY_VALUE = """
            - all \033[3m(default)\033[0m
            - australia
            - china
            - east asia
            - european union
            - japan
            - total
            - united kingdom
            - united states
            """;
    protected final String COMMODITY_VALUE = """
            - all \033[3m(default)\033[0m
            - fruit
            - logs, wood, wood articles
            - milk powder, butter, cheese
            - non-food manufactured goods
            - mechanical machinery
            - meat
            - electrical machinery
            - fish, crustaceans, molluscs
            """;
    protected final String TRANSPORT_VALUE = """
            - all \033[3m(default)\033[0m
            - air
            - sea
            """;
    protected final String MEASUREMENT_VALUE = """
            - $ \033[3m(default)\033[0m
            - tonnes
            """;
    protected final String OUTPUT_M_AVERAGE = """
            ------------------------
            \033[1mMonthly average\033[0m | %s/%s
            - country : %s
            - commodity : %s
            - transport_mode : %s
            - measurement : %s
            -----> %s
            """;
    protected final String OUTPUT_M_TOTAL = """
            ------------------------
            \033[1mMonthly total\033[0m | %s/%s
            - country : %s
            - commodity : %s
            - transport_mode : %s
            - measurement : %s
            -----> %s
            """;
    protected final String OUTPUT_Y_AVERAGE = """
            ------------------------
            \033[1mYearly average\033[0m | %s
            - country : %s
            - commodity : %s
            - transport_mode : %s
            - measurement : %s
            -----> %s
            """;
    protected final String OUTPUT_Y_TOTAL = """
            ------------------------
            \033[1mYearly total\033[0m | %s
            - country : %s
            - commodity : %s
            - transport_mode : %s
            - measurement : %s
            -----> %s
            """;
    protected final String ITALIC_DEFAULT = "\033[3m" + "(default)" + "\033[0m";
    protected final String ITALIC_MONTH_MAX = "\033[3m" + "(until 07/2012)" + "\033[0m";
}

