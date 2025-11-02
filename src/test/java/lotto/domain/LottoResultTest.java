package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    private LottoResult lottoResult;
    private Lottos lottos;

    @BeforeEach
    void init() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);
        lottoResult = new LottoResult(winningNumbers);

        // 3개 일치 1개, 5개 일치 1개 (보너스 번호 불일치) 나머지는 꽝, 9장 구매
        List<Lotto> purchasedLotto = List.of(
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),    // 5등 (3개 일치)
                new Lotto(List.of(45, 44, 43, 42, 41, 40)),
                new Lotto(List.of(45, 44, 43, 39, 1, 2)),
                new Lotto(List.of(38, 37, 36, 3, 4, 35)),
                new Lotto(List.of(6, 5, 4, 3, 15, 2)),    // 3등 (5개 일치)
                new Lotto(List.of(45, 44, 43, 42, 41, 40)),
                new Lotto(List.of(45, 44, 43, 39, 1, 2)),
                new Lotto(List.of(45, 44, 43, 42, 41, 40)),
                new Lotto(List.of(45, 44, 43, 39, 1, 2))
        );

        lottos = new Lottos(purchasedLotto);
    }

    @Test
    void 당첨_통계를_정확히_계산한다() {
        //given

        //when
        Map<Rank, Long> purchasedLottoStatistics = lottoResult.calculateStatistics(lottos);

        //then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals(1L, purchasedLottoStatistics.get(Rank.FIFTH)),
                    () -> assertEquals(1L, purchasedLottoStatistics.get(Rank.THIRD)),
                    () -> assertEquals(7L, purchasedLottoStatistics.get(Rank.NONE))
            );
        });
    }

    @Test
    void 수익률을_정확히_검증한다() {
        //given
        // 총 상금 = 5,000(5등) + 1,500,000(3등) = 1,505,000원
        // 총 비용 = 9 * 1,000 = 9,000원
        // 수익률 = (1,505,000 ÷ 9,000) × 100 = 16,722.2%

        //when
        Double purchasedLottoCalculateRate = lottoResult.calculateRate(lottos);

        //then
        assertSimpleTest(() -> {
            assertEquals(16722.2, purchasedLottoCalculateRate);
        });
    }

    @Test
    void 모두_낙첨이면_수익률이_0이다() {
        //given
        List<Lotto> allLoseLotto = List.of(
                new Lotto(List.of(45, 44, 43, 42, 41, 40)),
                new Lotto(List.of(45, 44, 43, 39, 1, 2)),
                new Lotto(List.of(38, 37, 36, 3, 4, 35))
        );
        Lottos allLoseLottos = new Lottos(allLoseLotto);

        //when
        Double purchasedLottoCalculateRate = lottoResult.calculateRate(allLoseLottos);

        //then
        assertSimpleTest(() -> {
            assertEquals(0.0, purchasedLottoCalculateRate);
        });
    }
}