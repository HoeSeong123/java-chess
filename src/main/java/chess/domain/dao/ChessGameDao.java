package chess.domain.dao;

import chess.domain.GameStatus;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessGameDao {
    private final Connection connection;

    public ChessGameDao(Connection connection) {
        this.connection = connection;
    }

    public void addChessGame(GameStatus gameStatus) {
        final var query = "INSERT INTO game(game_status) VALUES(?)";
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
            String tmp = resultSet.getString("game_status");
            System.out.println(tmp);
            return GameStatus.findGameStatus(tmp);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
