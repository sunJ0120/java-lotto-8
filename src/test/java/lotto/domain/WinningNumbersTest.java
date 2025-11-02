package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinningNumbersTest {
    private Lotto winningLotto;
    private WinningNumbers winningNumbers;

    @BeforeEach
    public void init() {
        winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        winningNumbers = new WinningNumbers(winningLotto, 7);
    }

    @Test
    void 보너스번호가_1미만_이면_예외가_발생한다() {
        // given
        int bonusNumber = 0;

        // when & then
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 보너스번호가_45초과_이면_예외가_발생한다() {
        // given
        int bonusNumber = 46;

        // when & then
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 보너스번호가_중복이면_예외가_발생한다() {
        // given
        int bonusNumber = 6;

        // when & then
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 번호가_3개_일치하면_5등이다() {
        //given
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        // when & then
        assertSimpleTest(() -> {
            Assertions.assertEquals(Rank.FIFTH, winningNumbers.calculateRank(purchasedLotto));
        });
    }

    @Test
    void 번호가_4개_일치하면_4등이다() {
        //given
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 11, 12));

        // when & then
        assertSimpleTest(() -> {
            Assertions.assertEquals(Rank.FOURTH, winningNumbers.calculateRank(purchasedLotto));
        });
    }

    @Test
    void 번호가_5개_일치하면_3등이다() {
        //given
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 12));

        // when & then
        assertSimpleTest(() -> {
            Assertions.assertEquals(Rank.THIRD, winningNumbers.calculateRank(purchasedLotto));
        });
    }

    @Test
    void 번호가_5개_일치하고_보너스번호가_있으면_2등이다() {
        //given
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when & then
        assertSimpleTest(() -> {
            Assertions.assertEquals(Rank.SECOND, winningNumbers.calculateRank(purchasedLotto));
        });
    }

    @Test
    void 번호가_6개_일치하면_1등이다() {
        //given
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertSimpleTest(() -> {
            Assertions.assertEquals(Rank.FIRST, winningNumbers.calculateRank(purchasedLotto));
        });
    }

    @Test
    void 번호가_4개_이하로_일치하면_보너스번호_일치여부를_무시한다() {
        //given

        // when & then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals(Rank.NONE,
                            winningNumbers.calculateRank(new Lotto(List.of(1, 2, 10, 11, 12, 7)))),
                    () -> assertEquals(Rank.FIFTH,
                            winningNumbers.calculateRank(new Lotto(List.of(1, 2, 3, 11, 12, 7)))),
                    () -> assertEquals(Rank.FOURTH,
                            winningNumbers.calculateRank(new Lotto(List.of(1, 2, 3, 4, 12, 7))))
            );
        });
    }
}