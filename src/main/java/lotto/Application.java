package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        InputView inputView = new InputView(inputParser);
        OutputView outputView = new OutputView();

        LottoService lottoService = new LottoService();
        LottoController controller = new LottoController(inputView, lottoService, outputView);

        controller.run();
    }
}
