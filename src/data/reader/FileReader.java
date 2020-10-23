package data.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> readFromFile(String filePath) {
        Scanner scanner = null;
        try {
            scanner = createFileScanner(filePath);
        } catch (FileNotFoundException e) {
            return List.of();
        }

        ArrayList<String> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }

        return result;
    }

    private Scanner createFileScanner(String path) throws FileNotFoundException {
        File file = new File(path);

        return new Scanner(file);
    }

    public void readAndPrint() {
        File f = new File(FileConstants.FIRST_FILE_NAME);

        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (scanner == null) return;

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }


}
