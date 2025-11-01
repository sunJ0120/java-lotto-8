package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class CompareLottoService {
    private InputView inputView;
    private InputParser inputParser;
    private OutputView outputView;

    public CompareLottoService(InputView inputView, InputParser inputParser, OutputView outputView) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.outputView = outputView;
    }

    public void compareAndPrintResult(Lottos lottos) {
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
