package lotto;

import lotto.controller.LottoController;
import lotto.service.CompareLottoService;
import lotto.service.PurchaseLottoService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputParser inputParser = new InputParser();
        OutputView outputView = new OutputView();

        PurchaseLottoService purchaseLottoService = new PurchaseLottoService(inputView, outputView);
        CompareLottoService compareLottoService = new CompareLottoService(inputView, inputParser, outputView);

        LottoController controller = new LottoController(purchaseLottoService, compareLottoService);
        controller.run();
    }
}
