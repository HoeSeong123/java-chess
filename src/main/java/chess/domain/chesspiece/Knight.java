package chess.domain.chesspiece;

import static chess.domain.chesspiece.Role.BLACK_KNIGHT;
import static chess.domain.chesspiece.Role.WHITE_KNIGHT;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public List<Position> findRoute(Position source, Position target, Piece targetPiece) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int colDistance = source.calculateRankDistance(target);

        if (!((fileDistance == 2 && colDistance == 1) || (fileDistance == 1 && colDistance == 2))) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
