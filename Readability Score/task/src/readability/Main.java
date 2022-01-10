package readability;

public class Main {
    public static void main(String[] args) {

        String input = InputReader.getInput(args[0]);       //get string from a file
        Expert expert = Expert.createAndAnalyze(input);     //analyze a text
        System.out.println(expert);
        ReadabilityTests.start(expert);      //get scores of the text by different tests.


    }


}

