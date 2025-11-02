package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {
    private static final String FORMAT_PURCHASE_COUNT = "%d개를 구매했습니다.";
    private static final String FORMAT_PRIZE_COUNT = "%s - %d개";
    private static final String FORMAT_PROFIT_RATE = "총 수익률은 %.1f%%입니다.";

    private static final String MESSAGE_STATISTICS_TITLE = "당첨 통계";
    private static final String MESSAGE_DIVIDER = "---";

    private static final long DEFAULT_COUNT = 0L;

    public void printLottos(Lottos lottos) {
        System.out.println(String.format(FORMAT_PURCHASE_COUNT,lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printPrizeStatistics(LottoResult lottoResult, Lottos lottos) {
        System.out.println(MESSAGE_STATISTICS_TITLE);
        System.out.println(MESSAGE_DIVIDER);

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            long count = lottoResult.calculateStatistics(lottos).getOrDefault(rank, DEFAULT_COUNT);
            System.out.println(String.format(FORMAT_PRIZE_COUNT, rank.getMessage(), count));
        }

        System.out.println(String.format(FORMAT_PROFIT_RATE, lottoResult.calculateRate(lottos)));
    }
}
