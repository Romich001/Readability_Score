package readability;

import java.util.Scanner;

public class ReadabilityTests {

    private double smogScore;
    private double colemanLiauScore;
    private double ariScore;
    private double fKScore;
    private double numWords;
    private double numSents;
    private double numChars;
    private double numSyllables;
    private double numPolySyllables;

    private ReadabilityTests(Expert expert) {

        numChars = expert.getNumChars();
        numSents = expert.getSentences().length;
        numWords = expert.getWords().length;
        numSyllables = expert.getNumSyllables();
        numPolySyllables = expert.getPolySyllables();

    }
//entry point of the class
    public static void start(Expert expert){
        ReadabilityTests rt = new ReadabilityTests(expert);
        rt.manageTests();
    }

    public void manageTests() {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        try(Scanner scanner = new Scanner(System.in)) {
            var userInput = scanner.nextLine();
            System.out.println();
            switch (userInput) {
                case "ARI":
                    automatedIndex();
                    break;
                case "FK":
                    flechKincaidTest();
                    break;
                case "SMOG":
                    smogTest();
                    break;
                case "CL":
                    colemanLiauTest();
                    break;
                case "all":
                    automatedIndex();
                    flechKincaidTest();
                    smogTest();
                    colemanLiauTest();
                    System.out.println();
                    var age = Ages.findAges(ariScore) + Ages.findAges(smogScore) + Ages.findAges(colemanLiauScore) +
                            Ages.findAges(fKScore);
                    System.out.printf("This text should be understood in average by %.2f-year-olds.", (double) age / 4);
                    break;
                default:
                    System.out.println("Error in manageTests()-switch-default.");
            }

        }
    }
//https://en.wikipedia.org/wiki/SMOG
    private void smogTest() {
        smogScore = 1.043 * Math.sqrt(numPolySyllables * 30 / numSents) + 3.1291;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (%s-year-olds).\n", smogScore, Ages.findAges(smogScore));
    }

    private void automatedIndex(){
        ariScore = 4.71 * numChars / numWords + 0.5 * numWords / numSents - 21.43;
        System.out.printf("Automated Readability Index: %.2f (%s-year-olds).\n", ariScore, Ages.findAges(ariScore));
    }
//https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests
    private void flechKincaidTest() {

        fKScore = 0.39 * numWords / numSents + 11.8 * numSyllables / numWords - 15.59;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (%s-year-olds).\n", fKScore, Ages.findAges(fKScore));
    }
//https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index
    private void colemanLiauTest() {

        var averageChars = numChars / numWords * 100;   // show average chars per 100 words
        var averageWords = numSents / numWords * 100;   // show average words per 100 sentances

        colemanLiauScore = 0.0588 * averageChars - 0.296 * averageWords - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (%s-year-olds).\n", colemanLiauScore, Ages.findAges(colemanLiauScore));

    }


}
