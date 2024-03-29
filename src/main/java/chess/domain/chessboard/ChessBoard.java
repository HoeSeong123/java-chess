package chess.domain.chessboard;

import static chess.domain.GameStatus.GAME_OVER;
import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public GameStatus move(Position source, Position target, GameStatus gameStatus) {
        checkEmpty(source);

        Piece piece = chessBoard.get(source);
        piece.checkValidMove(gameStatus);

        checkTargetIsTeam(piece, target);
        piece.findRoute(source, target, isEmpty(target))
                .forEach(this::checkObstacle);

        return replacePieceToTarget(source, target, gameStatus);
    }

    private void checkEmpty(Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 공간에는 기물이 존재하지 않습니다.");
        }
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

    private GameStatus replacePieceToTarget(Position source, Position target, GameStatus turn) {
        if (!isEmpty(target) && chessBoard.get(target)
                .isKing()) {
            return GAME_OVER;
        }
        Piece piece = chessBoard.get(source);
        chessBoard.remove(source);
        chessBoard.put(target, piece);

        return turn.changeTurn();
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public Map<Team, Score> calculateTotalScore() {
        Map<Team, Score> score = new HashMap<>();

        score.put(WHITE, calculateTeamScore(WHITE));
        score.put(BLACK, calculateTeamScore(BLACK));

        return score;
    }

    private Score calculateTeamScore(Team team) {
        Score score = new Score(0);
        for (File file : File.values()) {
            List<Piece> pieces = chessBoard.keySet()
                    .stream()
                    .filter(position -> chessBoard.get(position)
                            .getTeam() == team)
                    .filter(position -> position.hasFile(file))
                    .map(chessBoard::get)
                    .toList();

            score = calculateScore(score, pieces);
        }

        return score;
    }

    private static Score calculateScore(Score score, List<Piece> pieces) {
        boolean hasSameFilePawn = pieces.stream()
                .filter(Piece::isPawn)
                .count() > 1;

        for (Piece piece : pieces) {
            score = piece.calculateScore(score, hasSameFilePawn);
        }

        return score;
    }
}
