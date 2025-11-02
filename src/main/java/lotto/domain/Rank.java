package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private static final String FORMAT_MATCH_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%,d원)";
    private static final String FORMAT_MATCH_ONLY = "%d개 일치 (%,d원)";
    private final int matchLottoCount;
    private final boolean bonusMatch;
    private final long prize;

    Rank(int matchLottoCount, boolean bonusMatch, int prize) {
        this.matchLottoCount = matchLottoCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static Rank valueOf(int matchLottoCount, boolean bonusMatch) {
        if (matchLottoCount == 5 && bonusMatch) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchLottoCount == matchLottoCount)
                .filter(rank -> !rank.bonusMatch)
                .findFirst()
                .orElse(Rank.NONE);
    }

    public String getMessage() {
        if (this == NONE) {
            return "";
        }
        if (this == SECOND) {
            return String.format(FORMAT_MATCH_WITH_BONUS, matchLottoCount, prize);
        }
        return String.format(FORMAT_MATCH_ONLY, matchLottoCount, prize);
    }

    public long getPrize() {
        return prize;
    }
}
