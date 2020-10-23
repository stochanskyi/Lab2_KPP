package execution;

import java.util.Scanner;

public class AppScanner {
    public String getLine() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
