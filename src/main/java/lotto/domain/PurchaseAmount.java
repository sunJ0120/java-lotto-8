package lotto.domain;

import static lotto.util.LottoConstants.ERROR_INVALID_PURCHASE_UNIT;
import static lotto.util.LottoConstants.ERROR_NOT_NUMBER;
import static lotto.util.LottoConstants.UNIT;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(String input) {
        this.amount = validate(input);
    }

    private int validate(String number) {
        int amount = parseNumber(number);
        validateUnit(amount);
        return amount;
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ERROR_NOT_NUMBER, number)
            );
        }
    }

    private void validateUnit(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException(ERROR_INVALID_PURCHASE_UNIT);
        }
    }

    public int getValue() {
        return amount;
    }
}
