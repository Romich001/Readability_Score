package readability;

public enum Ages {

    KINDERGARTEN(1, 6),
    SECOND_GRADE(2, 7),
    THIRD_GRADE(3, 8),
    FORTH_GRADE(4, 9),
    FIFTH_GRADE(5, 10),
    SIXTH_GRADE(6, 11),
    SEVENTH_GRADE(7, 12),
    EIGHTH_GRADE(8, 13),
    NINTH_GRADE(9, 14),
    TENTH_GRADE(10, 15),
    ELEVENTH_GRADE(11, 17),
    TWELFTH_GRADE(12, 18),
    COLLEGE_STUDENT(13, 24),
    PROFESSOR(14, 25);

    double score;
    int age;

    Ages(double score, int age){
        this.score = score;
        this.age = age;
    }

    public static int findAges(double curScore) {
        curScore = Math.ceil(curScore);
        for(Ages age : values()){
            if(age.score == curScore) return age.age;
        }
        return -1;
    }
}
