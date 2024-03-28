package chess.domain.chessboard;

import chess.domain.Turn;
import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    private ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        return new ChessBoard(ChessBoardGenerator.initializeBoard());
    }

    public Turn move(Position source, Position target, Turn turn) {
        Piece piece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);

        turn.checkValidMove(piece);

        checkTargetIsTeam(piece, targetPiece);
        piece.findRoute(source, target, targetPiece)
                .forEach(this::checkObstacle);

        replacePieceToTarget(source, target, piece);

        return turn.changeTurn();
    }

    private void checkObstacle(Position position) {
        if (!chessBoard.get(position)
                .isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void checkTargetIsTeam(Piece source, Piece target) {
        if(source.isTeam(target)) {
            throw new IllegalStateException("같은 팀이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    private void replacePieceToTarget(Position source, Position target, Piece piece) {
        chessBoard.put(source, new Empty());
        chessBoard.put(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
