package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottosTest {
    @Test
    void generate로_요청한_개수만큼_로또가_생성된다() {
        //given

        // when & then
        assertSimpleTest(() -> {
            assertAll(
                    () -> assertEquals(3, Lottos.generate(3).size()),
                    () -> assertEquals(5, Lottos.generate(5).size()),
                    () -> assertEquals(10, Lottos.generate(10).size()),
                    () -> assertEquals(1000, Lottos.generate(1000).size())
            );
        });
    }

    @Test
    void getLottos로_반환된_리스트는_불변이다() {
        //given
        Lottos lottos = Lottos.generate(3);

        //when
        List<Lotto> lottoList = lottos.getLottos();

        //then
        assertSimpleTest(() -> {
            assertThatThrownBy(
                    () -> lottoList.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)))
            ).isInstanceOf(UnsupportedOperationException.class);
        });
    }

    @Test
    void iterator로_모든_로또를_순회할_수_있다() {
        //given
        Lottos lottos = Lottos.generate(3);
        int count = 0;

        //when
        for (Lotto lotto : lottos) {
            count++;
        }

        //then
        assertEquals(3, count);
    }
}