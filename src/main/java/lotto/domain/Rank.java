package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int matchLottoCount;
    private final boolean bonusMatch;
    private final long prize;

    Rank(int matchLottoCount, boolean bonusMatch, int prize) {
        this.matchLottoCount = matchLottoCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static Rank valueOf(int matchLottoCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchLottoCount == matchLottoCount)
                .filter(rank -> rank.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(Rank.NONE);
    }

    public long getPrize() {
        return prize;
    }
}
