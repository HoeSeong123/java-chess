package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FileTest {

    @Test
    @DisplayName("가로는 a~h의 알파벳으로 생성된다.")
    void makeFileTest() {
        var result = File.from("a");
        assertThat(result).isInstanceOf(File.class);
    }

    @Test
    @DisplayName("a~h가 아닌 알파벳으로 만들어진 가로는 존재하지 않는다.")
    void testValueOf() {
        assertThatThrownBy(() -> File.from("z")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값을 넘겨주어 file을 최신화한다.")
    void File_Update_with_value() {
        File file = File.from("a");

        assertThat(file.update(1)).isEqualTo(File.from("b"));
    }

    @Test
    @DisplayName("file과 file의 차이를 구한다.")
    void file_Subtract_with_other_file() {
        File file = File.from("a");
        var sut = File.from("c");

        var result = sut.subtractFile(file);

        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"a, b, 1", "a, a, 0", "b, a, -1"})
    @DisplayName("rank과 rank의 크기를 비교하여 방향을 찾는다..")
    void Rank_Find_direction_with_other_rank(String value1, String value2, int compareResult) {
        var sut = File.from(value1);
        File file = File.from(value2);

        var result = sut.findDirection(file);

        assertThat(result).isEqualTo(compareResult);
    }
}