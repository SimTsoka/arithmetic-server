package UnitTests.GamesTests.BasicArithmetic;

import Games.BasicArithmetic;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class GameGenerationTests {

    @Test
    void testDefaultGameGeneration() {
        BasicArithmetic defaultGame = new BasicArithmetic();
        TreeMap<String[], Integer> generatedQsAndAs = defaultGame.getQuestionsAndAnswers();

    }

    boolean validateQsAndAs(TreeMap<String[], Integer> qAndAs) {
        boolean isQAndACorrect = false;
        for (String[] Q: qAndAs.navigableKeySet()) {
            isQAndACorrect = isValidQuestion(Q);
//            isAnswer
        }
    }

    boolean isValidQuestion(String[] question) {
        return isInteger(question[0]) && isValidOperation(question[1]) && isInteger(question[2]);
    }

    boolean isInteger(String value) {
        return value.matches("^[-+]?\\d+$");
    }

    boolean isValidOperation(String operation) {
        return operation.matches("^[+\\-x/]+$");
    }

    boolean isAnswerCorrect(String[] question, Integer answer) {
//        Integer.parseInt(question)
    }
}
