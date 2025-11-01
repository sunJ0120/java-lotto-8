package lotto.service;

import lotto.domain.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class PurchaseLottoService {
    private InputView inputView;
    private OutputView outputView;

    public PurchaseLottoService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Lottos buyLottos() {
        int purchaseAmount = inputView.readPurchaseAmount();
        System.out.println();
        int purchaseCount = purchaseAmount / 1000;

        Lottos lottos = Lottos.generate(purchaseCount);
        outputView.printPurchaseCount(lottos);
        outputView.printLottos(lottos);
        System.out.println();

        return lottos;
    }
}
