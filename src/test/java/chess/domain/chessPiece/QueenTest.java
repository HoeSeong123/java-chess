package chess.domain.chessPiece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.chessPiece.Team.BLACK;
import static chess.domain.chessPiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {
    @Test
    @DisplayName("여왕은 앞뒤로 움직일 수 있다.")
    void Queen_Move_forward_and_backward() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getRoute(Position.from("a1"), Position.from("a4"));
        List<Position> positions = List.of(Position.from("a2"), Position.from("a3"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("여왕은 좌우로 움직일 수 있다.")
    void Queen_Move_side() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getRoute(Position.from("b4"), Position.from("e4"));
        List<Position> positions = List.of(Position.from("c4"), Position.from("d4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("여왕은 대각선으로 움직일 수 있다.")
    void Queen_Move_diagonal() {
        Piece piece = new Queen(WHITE);
        List<Position> route = piece.getRoute(Position.from("b2"), Position.from("e5"));
        List<Position> positions = List.of(Position.from("c3"), Position.from("d4"));
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Bishop_Validate_route() {
        Piece piece = new Queen(WHITE);
        assertThatThrownBy(() -> {
            piece.getRoute(Position.from("a1"), Position.from("b4"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Queen_Validate_team() {
        Piece piece=new Queen(WHITE);
        assertThat(piece.isTeam(new King(WHITE))).isTrue();
        assertThat(piece.isTeam(new King(BLACK))).isFalse();
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Queen_Check_pawn() {
        Piece piece = new Queen(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
