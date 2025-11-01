package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run(){
        InputView inputView = new InputView();
        InputParser inputParser = new InputParser();
        int purchaseAmount = inputView.readPurchaseAmount();
        System.out.println();
        int purchaseCount = purchaseAmount/1000;

        Lottos lottos = Lottos.generate(purchaseCount);
        OutputView outputView = new OutputView();
        outputView.printPurchaseCount(lottos);
        outputView.printLottos(lottos);
        System.out.println();

        String winningNumber = inputView.readWinningNumbers();
        List<Integer> winningNumbers = inputParser.parseWinningNumbers(winningNumber);
        System.out.println();

        Lotto winningLotto = new Lotto(winningNumbers);
        int bonusNumber = inputView.readBonusNumber();
        System.out.println();

        LottoResult lottoResult = new LottoResult(winningLotto, bonusNumber);
        outputView.printPrizeStatistics(lottoResult, lottos);
        System.out.println();
    }
}
