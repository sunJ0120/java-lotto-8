package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
