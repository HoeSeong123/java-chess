package chess.domain.dao;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.SQLException;

public class PieceDao {
    private final Connection connection;

    public PieceDao(Connection connection) {
        this.connection = connection;
    }

    public void addPiece(Piece piece) {
        final var query = "INSERT INTO piece(type, team) VALUES(?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            PieceMapper pieceMapper = PieceMapper.from(piece);
            preparedStatement.setString(1, pieceMapper.getType());
            preparedStatement.setString(2, pieceMapper.getTeam());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
