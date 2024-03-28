package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Queen extends SlidingPiece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (!source.isSameFile(target) && !source.isSameRank(target)
                && fileDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
