package lotto.view;

import static lotto.util.LottoConstants.ERROR_NOT_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.PurchaseAmount;
import lotto.util.InputParser;

public class InputView {
    private final static String INPUT_AMOUNT = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public int readPurchaseAmount() {
        System.out.println(INPUT_AMOUNT);
        return retryOnError(() -> {
            String input = Console.readLine();
            return new PurchaseAmount(input).getValue();
        });
    }

    public List<Integer> readWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS);
        return retryOnError(() -> {
            String input = Console.readLine();
            return inputParser.parseWinningNumbers(input);
        });
    }

    public int readBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        return retryOnError(() -> {
            String input = Console.readLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        String.format(ERROR_NOT_NUMBER, input)
                );
            }
        });
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return retryOnError(supplier);
        }
    }
}
