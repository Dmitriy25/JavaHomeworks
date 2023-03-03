import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public void printValidNumbers (String path) {
        Pattern pattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[-.]?\\d{4}$");
        InputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
            Scanner scanner = new Scanner(fis);
        while (scanner.hasNextLine()) {
            String number = scanner.nextLine();
            Matcher matcher = pattern.matcher(number);
            if (matcher.matches()) {
                System.out.println(number);
            }
        }
    }
}
