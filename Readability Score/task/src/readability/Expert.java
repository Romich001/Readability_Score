package readability;

import java.util.Locale;

public class Expert {

    private final String inputString;
    private final String[] words;             //all words from the text splited in the []
    private final String[] sentences;         // all sentences from the text
    private double numChars = 0.0;            // all chars in the text
    private int numSyllables = 0;             //all syllables in the
    private int polySyllables = 0;            // all polysyllable words in the text

    private Expert(String str) {
        inputString = str;
        words = inputString.split("\\s");
        sentences = inputString.strip().split("[.?!;] ");

    }

    public int getNumSyllables() {
        return numSyllables;
    }

    public int getPolySyllables() {
        return polySyllables;
    }

    public String[] getWords() {
        return words;
    }

    public String[] getSentences() {
        return sentences;
    }

    public double getNumChars() {
        return numChars;
    }
    //used to create expert and start expertize
    public static Expert createAndAnalyze(String str){
        Expert exp = new Expert(str);
        exp.expertiseStr();
        return exp;
    }

    private void expertiseStr() {
        findNumChars();      //count chars
        analyzeWords();      //count polysyllable words and syllables
    }
//count chars
    private void findNumChars() {
        for (String word :
                words) {
            numChars += word.length();
        }
    }
    //count polysyllable words and syllables
    private void analyzeWords() {

        for (String word :
                words) {
            word = prepareWord(word);
            var count = countSyllables(word);
            if(count > 2) polySyllables++;  // count number of polysyllables in the text
            numSyllables += count;          // number of syllables in the text
        }

    }
//count syllables
    private int countSyllables(String word) {
        var privVowel = false;   // if previous char is vowel
        var count = 0;   //number of syllables
        for (int i = 0; i < word.length(); i++) {     //loop all chars of the word
            char ch = word.charAt(i);
            if(!Character.isLetter(ch)) continue;      //if the char is not a letter
            if (isVowel(ch)) {     //check if two vowels a near one another
                if (!privVowel) {   //if previous char is not vowel
                    count++;
                    privVowel = true;
                }
            } else {
                privVowel = false;
            }
        }

        if (word.matches("\\w*e")) count--;     //don't count last e
        if (count == 0) count++;            //if no syllables then count it like 1

        return count;

    }
// used by analyzeWord() to trim and delete ,.!? from the end of word
    private String prepareWord(String word) {
        word = word.toLowerCase(Locale.ROOT).strip();//trim whitespaces, make word lower case
        if (word.matches("\\w*[.?;!]")) {
            word = word.substring(0, word.length() - 1);    //remove not letters from word's end
        }
        return word;
    }
//used by countSyllables() to check if char is a vowel
    private boolean isVowel(char ch) {
        return String.valueOf(ch).matches("[aeoiuy]");
    }


    @Override
    public String toString() {

        return "The text is:\n" +
                inputString +
                "\n\n" +
                "Words: " + words.length + "\n" +
                "Sentences: " + sentences.length + "\n" +
                "Characters: " + (int) numChars + "\n" +
                "Syllables: " + numSyllables + "\n" +
                "Polysyllables: " + polySyllables;

    }
}
