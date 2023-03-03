import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToJson {
    private List<User> users = new ArrayList<>();

    public void usersFromTxtToJson(String filePath) {

        File file = new File(filePath);
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fis);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] userNameAgeArr = scanner.nextLine().split(" ");
            users.add(new User(userNameAgeArr[0], Integer.parseInt(userNameAgeArr[1])));
        }
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter("user.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(users, fileWriter);
        System.out.println(gson.toJson(users));
    }
}
