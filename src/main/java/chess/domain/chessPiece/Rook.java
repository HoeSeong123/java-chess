package chess.domain.chessPiece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public class Rook extends Piece{

    public Rook(Team team) {
        super(team);
    }

    @Override
    public List<Position> getRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = direction.move(source);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = direction.move(movingPosition);
        }
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if(source.isDifferentColumn(target) && source.isDifferentRow(target)) {
            throw new IllegalArgumentException("x");
        }
    }

    @Override
    public Role getRole() {
        if(team.isWhite()) {
            return WHITE_ROOK;
        }
        return BLACK_ROOK;
    }
}
