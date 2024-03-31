package chess.domain.dao;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardGenerator;
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
        } catch (SQLException ignored) {
        }
    }

    @BeforeEach
    void setUp() {
        chessBoardDao = new ChessBoardDao(connection);
    }

    @Test
    @DisplayName("체스 보드를 추가한다.")
    void ChessBoardDao_Add_chessBoard() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.initializeBoard());
        assertThatCode(() -> chessBoardDao.addChessBoard(chessBoard))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("체스 보드를 가져온다.")
    void ChessBoardDao_Load_chessBoard() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(
                        new Position("a", "1"), new King(BLACK),
                        new Position("b", "3"), new King(WHITE)
                ));
        chessBoardDao.addChessBoard(chessBoard);

        var result = chessBoardDao.loadChessBoard().getChessBoard();

        assertThat(result.get(new Position("a", "1"))).isEqualTo(new King(BLACK));
        assertThat(result.get(new Position("b", "3"))).isEqualTo(new King(WHITE));
    }
}
