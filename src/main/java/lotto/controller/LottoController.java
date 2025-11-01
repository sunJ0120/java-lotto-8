package lotto.controller;

import lotto.domain.Lottos;
import lotto.service.CompareLottoService;
import lotto.service.PurchaseLottoService;

public class LottoController {
    private PurchaseLottoService purchaseLottoService;
    private CompareLottoService compareLottoService;

    public LottoController(PurchaseLottoService purchaseLottoService, CompareLottoService compareLottoService) {
        this.purchaseLottoService = purchaseLottoService;
        this.compareLottoService = compareLottoService;
    }

    public void run() {
        Lottos lottos = purchaseLottoService.buyLottos();
        compareLottoService.compareAndPrintResult(lottos);
    }
}
