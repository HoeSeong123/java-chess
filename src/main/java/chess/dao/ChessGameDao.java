package chess.dao;

import chess.domain.GameStatus;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessGameDao {
    private final Connection connection;

    public ChessGameDao(Connection connection) {
        this.connection = connection;
    }

    public void updateGameStatus(GameStatus gameStatus) {
        final var query = "UPDATE game SET game_status = ? LIMIT 1";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameStatus.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public GameStatus findGameStatus() {
        final var query = "SELECT game_status FROM game LIMIT 1";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return GameStatus.findGameStatus(resultSet.getString("game_status"));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long findId() {
        final var query = "SELECT id FROM game LIMIT 1";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong("id");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}