package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
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
        String winningNumberInput = inputView.readWinningNumbers();
        List<Integer> parseWinningNumbers = inputParser.parseWinningNumbers(winningNumberInput);
        System.out.println();

        Lotto winningLotto = new Lotto(parseWinningNumbers);
        int bonusNumber = inputView.readBonusNumber();
        System.out.println();

        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        LottoResult lottoResult = new LottoResult(winningNumbers);
        outputView.printPrizeStatistics(lottoResult, lottos);
        System.out.println();
    }
}
