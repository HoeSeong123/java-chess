package chess.dao;

import static chess.domain.chesspiece.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.ConnectionGenerator;
import chess.dao.PieceDao;
import chess.domain.chesspiece.Piece;
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
    @DisplayName("기물 정보를 통해 id를 가져온다.")
    void PieceDao_Find_id_by_piece() {
        Piece piece = new King(BLACK);

        var result = pieceDao.findIdByPiece(piece);

        assertThat(result).isEqualTo((byte) 2);
    }

    @Test
    @DisplayName("id를 통해 해당 기물 정보를 가져온다.")
    void PieceDao_Find_piece_by_id() {
        var result = pieceDao.findPieceById((byte) 2);
        assertThat(result).isEqualTo(new King(BLACK));
    }
}
