package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호_범위가_1미만_일때_예외가_발생한다() {
        //when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 0, 4, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호_범위가_45초과_일때_예외가_발생한다() {
        //when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 46, 4, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 일치하는_번호의_갯수가_0개인_경우() {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purcharseLotto = new Lotto(List.of(8, 9, 10, 11, 43, 45));

        //when
        int matchingNumber = winningLotto.countMatches(purcharseLotto);

        //then
        assertSimpleTest(() -> {
            Assertions.assertEquals(0, matchingNumber);
        });
    }

    @Test
    void 일치하는_번호가_일부인_경우() {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purcharseLotto = new Lotto(List.of(1, 45, 32, 4, 19, 6));

        //when
        int matchingNumber = winningLotto.countMatches(purcharseLotto);

        //then
        assertSimpleTest(() -> {
            Assertions.assertEquals(3, matchingNumber);
        });
    }

    @Test
    void 뽑은_번호가_포함되어_있지_않은_경우() {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 8;

        //when
        boolean isContain = winningLotto.contains(bonusNumber);

        //then
        assertSimpleTest(() -> {
            Assertions.assertFalse(isContain);
        });
    }

    @Test
    void 뽑은_번호가_포함되어_있는_경우() {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 3;

        //when
        boolean isContain = winningLotto.contains(bonusNumber);

        //then
        assertSimpleTest(() -> {
            Assertions.assertTrue(isContain);
        });
    }

    @Test
    void 생성자에서_오름차순_정렬이_잘_되는지_확인() {
        //given
        List<Integer> unsortedNumbers = List.of(45, 43, 32, 44, 1, 20);

        //when
        Lotto lotto = new Lotto(unsortedNumbers);

        //then
        assertSimpleTest(() -> {
            Assertions.assertEquals(
                    List.of(1, 20, 32, 43, 44, 45).toString(), lotto.toString()
            );
        });
    }

    @Test
    void 로또_번호의_개수가_6개_이하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO:  null 리스트나 빈 리스트 전달 시
}
