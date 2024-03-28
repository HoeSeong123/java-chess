package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class King extends SlidingPiece {

    public King(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (fileDistance != 1 && columnDistance != 1) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
