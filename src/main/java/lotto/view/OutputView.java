package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {
    public void printPurchaseCount(Lottos lottos) {
        System.out.printf("%d개를 구매하였습니다.%n", lottos.size());
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printPrizeStatistics(LottoResult lottoResult, Lottos lottos) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            long count = lottoResult.calculateStatistics(lottos).getOrDefault(rank, 0L);
            System.out.println(rank.getMessage() + " - " + count + "개");
        }

        System.out.printf("총 수익률은 %.1f%% 입니다.", lottoResult.calculateRate(lottos));
    }
}
