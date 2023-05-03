import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ReadCsv readCsv = new ReadCsv();
        UI ui = new UI();
        ui.start();
        ui.running();
    }
}
