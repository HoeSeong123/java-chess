package chess.domain.chessboard;

import chess.domain.Turn;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Turn move(Position source, Position target, Turn turn) {
        if(isEmpty(source)) {
            throw new IllegalArgumentException("해당 공간에는 기물이 존재하지 않습니다.");
        }
        Piece piece = chessBoard.get(source);

        turn.checkValidMove(piece);

        checkTargetIsTeam(piece, target);
        piece.findRoute(source, target, isEmpty(target))
                .forEach(this::checkObstacle);

        replacePieceToTarget(source, target, piece);

        return turn.changeTurn();
    }

    private boolean isEmpty(Position target) {
        return !chessBoard.containsKey(target);
    }

    private void checkObstacle(Position position) {
        if (!isEmpty(position)) {
            throw new IllegalArgumentException("방해물이 있어 이동할 수 없습니다.");
        }
    }

    private void checkTargetIsTeam(Piece source, Position targetPosition) {
        if (!isEmpty(targetPosition) && source.isTeam(chessBoard.get(targetPosition))) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    private void replacePieceToTarget(Position source, Position target, Piece piece) {
        chessBoard.remove(source);
        chessBoard.put(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
