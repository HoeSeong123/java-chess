package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Team team;
    private final Score score;

    public Piece(Team team, Score score) {
        this.team = team;
        this.score = score;
    }

    public abstract List<Position> findRoute(Position source, Position target, boolean isEmpty);

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract boolean isPawn();

    public abstract boolean isEmpty();

    public final Team getTeam() {
        return team;
    }

    public final boolean isTeam(Piece piece) {
        return team == piece.team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
