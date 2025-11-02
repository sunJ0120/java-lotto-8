package lotto.domain;

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
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (1 > bonusNumber || bonusNumber > 45) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 로또 번호는 1 ~ 45까지의 범위만 허용합니다. : %d", bonusNumber)
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
