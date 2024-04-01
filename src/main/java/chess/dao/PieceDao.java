package chess.dao;

import chess.domain.chesspiece.Piece;
import java.sql.Connection;
import java.sql.SQLException;

public class PieceDao {
    private final Connection connection;

    public PieceDao(Connection connection) {
        this.connection = connection;
    }

    public byte findIdByPiece(final Piece piece) {
        final var query = "SELECT id FROM piece WHERE type = ? AND team = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            PieceMapper pieceMapper = PieceMapper.from(piece);
            preparedStatement.setString(1, pieceMapper.getType());
            preparedStatement.setString(2, pieceMapper.getTeam());

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getByte("id");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece findPieceById(byte id) {
        final var query = "SELECT type, team FROM piece WHERE id = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setByte(1, id);

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String type = resultSet.getString("type");
            String team = resultSet.getString("team");
            return PieceMapper.mapToPiece(type, team);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
