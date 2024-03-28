package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, a, 2, UP",
            "a, 2,a, 1,DOWN",
            "b, 1, a, 1, LEFT",
            "a, 1, b, 1, RIGHT",
            "b, 1, a, 2, UP_LEFT",
            "a, 1, b, 2,UP_RIGHT",
            "b, 2, a, 1, DOWN_LEFT",
            "a, 2, b, 1, DOWN_RIGHT"})
    @DisplayName("출발지와 목적지를 넘겨주면 방향을 가져온다.")
    void Direction_Find_direction_with_positions(String file1,
                                                 String rank1,
                                                 String file2,
                                                 String rank2,
                                                 Direction inputDirection) {
        Position source = new Position(file1, rank1);
        Position target = new Position(file2, rank2);
        Direction direction = Direction.findDirection(source, target);

        assertThat(direction).isEqualTo(inputDirection);
    }
}
