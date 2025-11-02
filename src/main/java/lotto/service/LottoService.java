package lotto.service;

import static lotto.util.LottoConstants.UNIT;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoService {
    public Lottos purchaseLottos(int purchaseAmount) {
        int purchaseCount = purchaseAmount / UNIT;
        return Lottos.generate(purchaseCount);
    }

    public WinningNumbers createWinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        Lotto winningLotto = new Lotto(winningNumbers);
        return new WinningNumbers(winningLotto, bonusNumber);
    }

    public LottoResult createLottoResult(WinningNumbers winningNumbers) {
        return new LottoResult(winningNumbers);
    }
}
