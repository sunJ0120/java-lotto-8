package lotto.domain;

import lotto.Lotto;

public class WinningNumbers {
    private Lotto winningLotto;
    private int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }
}
