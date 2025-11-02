package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public LottoResult(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private Rank calculateRank(Lotto other) {
        int matchLottoCount = winningLotto.countMatches(other);
        boolean hasBonusNumber = other.contains(bonusNumber);

        return Rank.valueOf(matchLottoCount, hasBonusNumber);
    }

    public Map<Rank, Long> calculateStatistics(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::calculateRank)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.counting()
                ));
    }

    private long calculateTotalPrize(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::calculateRank)
                .mapToLong(Rank::getPrize)
                .sum();
    }

    public Double calculateRate(Lottos lottos) {
        long totalPrize = calculateTotalPrize(lottos);
        int totalCost = 1000 * lottos.size();
        double rate = ((double) totalPrize / totalCost) * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
