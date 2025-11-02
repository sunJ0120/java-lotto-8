package lotto.controller;

import java.util.List;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final LottoService lottoService;
    private final OutputView outputView;

    public LottoController(InputView inputView, LottoService lottoService, OutputView outputView) {
        this.inputView = inputView;
        this.lottoService = lottoService;
        this.outputView = outputView;
    }

    public void run() {
        Lottos lottos = purchaseLottos();
        WinningNumbers winningNumbers = receiveWinningNumbers();
        calculateAndPrintResult(winningNumbers, lottos);
    }

    private void calculateAndPrintResult(WinningNumbers winningNumbers, Lottos lottos) {
        LottoResult lottoResult = lottoService.createLottoResult(winningNumbers);
        outputView.printPrizeStatistics(lottoResult, lottos);
        System.out.println();
    }

    private WinningNumbers receiveWinningNumbers() {
        List<Integer> parseWinningNumbers = inputView.readWinningNumbers();
        System.out.println();

        int bonusNumber = inputView.readBonusNumber();
        System.out.println();

        return lottoService.createWinningNumbers(parseWinningNumbers, bonusNumber);
    }

    private Lottos purchaseLottos() {
        int purchaseAmount = inputView.readPurchaseAmount();
        System.out.println();

        Lottos lottos = lottoService.purchaseLottos(purchaseAmount);
        outputView.printPurchaseCount(lottos);
        outputView.printLottos(lottos);
        System.out.println();

        return lottos;
    }
}
