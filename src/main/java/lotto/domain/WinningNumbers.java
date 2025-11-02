package lotto.domain;

import static lotto.util.LottoConstants.ERROR_INVALID_SIZE;
import static lotto.util.LottoConstants.ERROR_NUMBER_OUT_OF_RANGE;
import static lotto.util.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.util.LottoConstants.MIN_LOTTO_NUMBER;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private static void validateBonusNumberDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (MIN_LOTTO_NUMBER > bonusNumber || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format(ERROR_NUMBER_OUT_OF_RANGE, bonusNumber)
            );
        }
    }

    public void validate(Lotto winningLotto, int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(winningLotto, bonusNumber);
    }

    public Rank calculateRank(Lotto purchasedLotto) {
        int matchLottoCount = winningLotto.countMatches(purchasedLotto);
        boolean hasBonusNumber = purchasedLotto.contains(bonusNumber);

        return Rank.valueOf(matchLottoCount, hasBonusNumber);
    }
}
