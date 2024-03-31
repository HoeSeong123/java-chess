package chess.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.GameStatus;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    private ChessGameDao chessGameDao;
    private static Connection connection;

    @BeforeAll
    static void connection() {
        try {
            connection = ConnectionGenerator.getConnection();
            connection.setAutoCommit(false);
        } catch(SQLException ignored) {
        }
    }

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao(connection);
    }

    @AfterEach
    void rollBack() {
        try {
            connection.rollback();
        } catch(SQLException ignored) {
        }
    }

    @Test
    @DisplayName("체스 게임을 업데이트한다.")
    void ChessGameDao_Add_chessGame() {
        assertThatCode(() -> chessGameDao.updateGameStatus(GameStatus.WHITE_TURN))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 상태를 가져온다.")
    void ChessGameDao_Get_gameStatus() {
        var result = chessGameDao.findGameStatus();
        assertThat(result).isEqualTo(GameStatus.WHITE_TURN);
    }

}
