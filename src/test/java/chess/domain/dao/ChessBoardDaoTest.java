package chess.domain.dao;

import static chess.domain.chesspiece.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.GameStatus;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardGenerator;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    private ChessBoardDao chessBoardDao;
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
        chessBoardDao = new ChessBoardDao(connection);
    }

    @Test
    @DisplayName("체스 보드를 추가한다.")
    void ChessGameDao_Add_chessGame() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.initializeBoard());
        assertThatCode(() -> chessBoardDao.addAll(chessBoard))
                .doesNotThrowAnyException();
    }

}
