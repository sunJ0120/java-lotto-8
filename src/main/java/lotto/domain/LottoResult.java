package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.Lotto;

public class LottoResult {
    private final Lotto winnigLotto;
    private final int bonusNumber;

    public LottoResult(Lotto winnigLotto, int bonusNumber) {
        this.winnigLotto = winnigLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto other) {
        int matchLottoCount = winnigLotto.countMatches(other);
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

    public long calculateTotalPrize(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::calculateRank)
                .mapToLong(Rank::getPrize)
                .sum();
    }

    public Double calculateRate(Lottos lottos) {
        long totalPrize = calculateTotalPrize(lottos);
        int totalCost = 1000 * lottos.size();
        Double rate = (double) (totalCost / totalPrize) * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
