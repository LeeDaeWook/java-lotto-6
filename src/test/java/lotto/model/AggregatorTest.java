package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AggregatorTest {

    @Test
    void result_6개_당첨_1개와_0개_당첨_1개_인경우() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of(1,2,3,4,5,6)));
        lottos.add(new Lotto(List.of(11,12,13,14,15,16)));

        List<Integer> winningNumbers = List.of(1,2,3,4,5,6);
        List<Integer> bonusNumber = new ArrayList<>();
        bonusNumber.add(7);

        DrawNumbers drawNumbers = new DrawNumbers(winningNumbers, bonusNumber);
        Aggregator aggregator = new Aggregator(drawNumbers);

        List<Integer> result = aggregator.matchResult(lottos);
        Assertions.assertThat(result).isEqualTo(List.of(6, 0));
    }

    @Test
    void result_당첨_번호와_5개가_일치하고_보너스번호가_일치하는_경우() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of(1,40,41,42,43,44)));
        lottos.add(new Lotto(List.of(11,12,13,14,15,16)));

        List<Integer> winningNumbers = List.of(40,41,42,43,44,45);
        List<Integer> bonusNumber = new ArrayList<>();
        bonusNumber.add(1);

        DrawNumbers drawNumbers = new DrawNumbers(winningNumbers, bonusNumber);
        Aggregator aggregator = new Aggregator(drawNumbers);

        List<Integer> result = aggregator.matchResult(lottos);
        Assertions.assertThat(result).isEqualTo(List.of(Rule.SECOND_RANK.value(), 0));
    }

    @Test
    void result_구입한_로또_번호들_중에서_일치하는_번호가_하나도_없는_경우() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of(1,2,3,4,5,6)));
        lottos.add(new Lotto(List.of(11,12,13,14,15,16)));

        List<Integer> winningNumbers = List.of(40,41,42,43,44,45);
        List<Integer> bonusNumber = new ArrayList<>();
        bonusNumber.add(39);

        DrawNumbers drawNumbers = new DrawNumbers(winningNumbers, bonusNumber);
        Aggregator aggregator = new Aggregator(drawNumbers);

        List<Integer> result = aggregator.matchResult(lottos);
        Assertions.assertThat(result).isEqualTo(List.of(0, 0));
    }
}
