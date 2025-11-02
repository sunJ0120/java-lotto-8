package lotto.domain;

import static lotto.util.LottoConstants.ERROR_INVALID_SIZE;
import static lotto.util.LottoConstants.ERROR_NUMBER_DUPLICATE;
import static lotto.util.LottoConstants.ERROR_NUMBER_OUT_OF_RANGE;
import static lotto.util.LottoConstants.LOTTO_SIZE;
import static lotto.util.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.util.LottoConstants.MIN_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sort(numbers);
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (MIN_LOTTO_NUMBER > number || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(
                        String.format(ERROR_NUMBER_OUT_OF_RANGE, number)
                );
            }
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException(ERROR_NUMBER_DUPLICATE);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private List<Integer> sort(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public int countMatches(Lotto other) {
        return (int) this.numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return this.numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
