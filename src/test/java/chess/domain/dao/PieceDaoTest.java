package chess.domain.dao;

import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.GameStatus;
import chess.domain.chesspiece.slidingPiece.King;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private PieceDao pieceDao;
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
        pieceDao = new PieceDao(connection);
    }

    @Test
    @DisplayName("체스 게임을 추가한다.")
    void ChessGameDao_Add_chessGame() {
        assertThatCode(() -> pieceDao.addPiece(new King(WHITE)));
    }
}
