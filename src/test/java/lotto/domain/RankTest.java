package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {
    @Test
    void 번호_3개가_일치하고_보너스번호_없으면_5등이다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(
                    Rank.FIFTH, Rank.valueOf(3, false)
            );
        });
    }

    @Test
    void 번호_4개가_일치하고_보너스번호_없으면_4등이다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(
                    Rank.FOURTH, Rank.valueOf(4, false)
            );
        });
    }

    @Test
    void 번호_5개가_일치하고_보너스번호_없으면_3등이다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(
                    Rank.THIRD, Rank.valueOf(5, false)
            );
        });
    }

    @Test
    void 번호_5개가_일치하고_보너스번호_있으면_2등이다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(
                    Rank.SECOND, Rank.valueOf(5, true)
            );
        });
    }

    @Test
    void 번호_6개가_일치하고_보너스번호_없으면_1등이다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(
                    Rank.FIRST, Rank.valueOf(6, false)
            );
        });
    }

    @Test
    void 번호_2개이하_일치하면_등수없다() {
        //when & then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals(Rank.NONE, Rank.valueOf(2, false)),
                    () -> assertEquals(Rank.NONE, Rank.valueOf(1, false)),
                    () -> assertEquals(Rank.NONE, Rank.valueOf(0, false))
            );
        });
    }

    @Test
    void 번호_2개_일치하면서_보너스번호_일치하면_등수없다() {
        //when & then
        assertSimpleTest(() -> {
            assertEquals(Rank.NONE, Rank.valueOf(2, true));
        });
    }

    @Test
    void 번호_3개_또는_4개_일치하면서_보너스번호_일치해도_보너스는_무시된다() {
        //when & then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals(Rank.FIFTH, Rank.valueOf(3, true)),
                    () -> assertEquals(Rank.FOURTH, Rank.valueOf(4, true))
            );
        });
    }

    @Test
    void 등수가_NONE_일때_메세지_확인하기() {
        //given
        Rank none = Rank.NONE;

        //when & then
        assertSimpleTest(() -> {
            assertThat(none.getMessage()).isEmpty();
        });
    }

    @Test
    void 등수가_SECOND_일때_메세지_확인하기() {
        //given
        Rank second = Rank.SECOND;

        //when & then
        assertSimpleTest(() -> {
            assertEquals("5개 일치, 보너스 볼 일치 (30,000,000원)", second.getMessage());
        });
    }

    @Test
    void 일반_등수_일때_메세지_확인하기() {
        //given
        Rank fifth = Rank.FIFTH;
        Rank fourth = Rank.FOURTH;
        Rank third = Rank.THIRD;
        Rank first = Rank.FIRST;

        //when & then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals("3개 일치 (5,000원)", fifth.getMessage()),
                    () -> assertEquals("4개 일치 (50,000원)", fourth.getMessage()),
                    () -> assertEquals("5개 일치 (1,500,000원)", third.getMessage()),
                    () -> assertEquals("6개 일치 (2,000,000,000원)", first.getMessage())
            );
        });
    }
}