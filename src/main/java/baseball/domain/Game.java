package baseball.domain;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> randomNumbers;
    private List<Integer> inputNumbers;
    private int[] comparisonResult;
    private Game(List<Integer> randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public static Game numberOf(List<Integer> randomNumbers) {
        return new Game(randomNumbers);
    }

    public void setInputNumber(String inputValue) {
        validateInputNumber(inputValue);
        inputNumbers = changeStringToList(inputValue);
    }

    private void validateInputNumber(String inputValue) {
        validateLengthThree(inputValue);
        validateEachNumber(inputValue);
    }

    private void validateLengthThree(String inputValue) {
        if(inputValue.length() != 3) {
            throw new IllegalArgumentException("입력값이 3자리가 아닙니다.");
        }
    }

    private void validateEachNumber(String inputValue) {
        List<Character> compareList = new ArrayList<>();
        for(char currentCharacter : inputValue.toCharArray()) {
            isBetweenOneAndNine(currentCharacter);
            if(compareList.contains(currentCharacter)) {
                throw new IllegalArgumentException("서로 다른 숫자가 아닙니다.");
            }
            compareList.add(currentCharacter);
        }
    }

    private void isBetweenOneAndNine(char currentCharacter) {
        if(!('1' <= currentCharacter && currentCharacter <= '9')) {
            throw new IllegalArgumentException("1 ~ 9 사이의 숫자가 아닙니다.");
        }
    }

    private List<Integer> changeStringToList(String inputValue) {
        List<Integer> inputNumbers = new ArrayList<>();
        for (char currentCharacter : inputValue.toCharArray()) {
            inputNumbers.add((int) (currentCharacter - '0'));
        }
        return inputNumbers;
    }

    public void compareNumber() {
        comparisonResult = new int[]{0, 0};
        for(int currentPosition = 0; currentPosition < randomNumbers.size(); currentPosition++) {
            compareInputNumber(currentPosition);
        }
    }

    private void compareInputNumber(int currentPosition) {
        if(randomNumbers.get(currentPosition) == inputNumbers.get(currentPosition)) {
            comparisonResult[1]++;
            return;
        }
        if(randomNumbers.contains(inputNumbers.get(currentPosition))) {
            comparisonResult[0]++;
        }
    }

    public boolean isThreeStrike() {
        return comparisonResult[1] == 3;
    }

    public boolean isOnlyStrike() {
        return comparisonResult[0] == 0 && comparisonResult[1] != 0;
    }

    public boolean isOnlyBall() {
        return comparisonResult[0] != 0 && comparisonResult[1] == 0;
    }

    public boolean isNothing() {
        return comparisonResult[0] == 0 && comparisonResult[1] == 0;
    }

    public int getBallNumber() {
        return comparisonResult[0];
    }

    public int getStrikeNumber() {
        return comparisonResult[1];
    }
}
