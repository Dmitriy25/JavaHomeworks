public class Main {
    public static void main(String[] args) {
        Validator v = new Validator();
        v.printValidNumbers("numbers.txt");

        Words w = new Words();
        w.wordsFrequency("words.txt");
    }
}