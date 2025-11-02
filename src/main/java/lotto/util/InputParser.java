package lotto.util;

import static lotto.util.LottoConstants.ERROR_EMPTY_INPUT;
import static lotto.util.LottoConstants.ERROR_NOT_NUMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DELIMITER = ",";

    public List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(this::parseNumber)
                .collect(Collectors.toList());
    }

    private int parseNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ERROR_NOT_NUMBER, number)
            );
        }
    }
}
