package chess.domain.dao;

import chess.domain.GameStatus;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoardDao {
    private final Connection connection;
    private final PieceDao pieceDao;

    public ChessBoardDao(Connection connection) {
        this.connection = connection;
        this.pieceDao = new PieceDao(connection);
    }

    public void addAll(ChessBoard chessBoard) {
        for(Entry<Position, Piece> position : chessBoard.getChessBoard().entrySet()) {
            addPosition(GameStatus.WHITE_TURN, position.getKey(), position.getValue());
        }
    }

    private void addPosition(GameStatus gameStatus, Position position, Piece piece) {
        final var query = "INSERT INTO board(game_status, position, piece_id) VALUES(?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameStatus.name());
            preparedStatement.setString(2, PositionConverter.convertToString(position));
            preparedStatement.setByte(3, pieceDao.findIdByPiece(piece));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
