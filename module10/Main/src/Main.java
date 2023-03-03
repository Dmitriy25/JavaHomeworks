public class Main {
    public static void main(String[] args) {
        System.out.println("Task №1");
        System.out.println("=-=-=-=-=");

        Validator v = new Validator();
        v.printValidNumbers("numbers.txt");

        System.out.println("=-=-=-=-=");
        System.out.println("\nTask №2");
        System.out.println("=-=-=-=-=");

        Words w = new Words();
        w.wordsFrequency("words.txt");

        System.out.println("=-=-=-=-=");
        System.out.println("\nTask №3");
        System.out.println("=-=-=-=-=");

        FileToJson f = new FileToJson();
        f.usersFromTxtToJson("file.txt");
        System.out.println("=-=-=-=-=");
    }
}