package chess.domain.chessboard;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    /*
    .KR.....
    P.PB....
    .P..Q...
    ........
    .....nq.
    .....p.p
    .....pp.
    ....rk..
     */
    @Test
    @DisplayName("각 팀의 점수를 계산한다.")
    void ChessBoard_Calculate_total_score() {
        ChessBoard chessBoard = new ChessBoard(
                Map.ofEntries(
                        Map.entry(new Position(File.b, Rank.EIGHT), new King(BLACK)),
                        Map.entry(new Position(File.c, Rank.EIGHT), new Rook(BLACK)),
                        Map.entry(new Position(File.a, Rank.SEVEN), new BlackPawn()),
                        Map.entry(new Position(File.c, Rank.SEVEN), new BlackPawn()),
                        Map.entry(new Position(File.d, Rank.SEVEN), new Bishop(BLACK)),
                        Map.entry(new Position(File.b, Rank.SIX), new BlackPawn()),
                        Map.entry(new Position(File.e, Rank.SIX), new Queen(BLACK)),
                        Map.entry(new Position(File.f, Rank.FOUR), new Knight(Team.WHITE)),
                        Map.entry(new Position(File.g, Rank.FOUR), new Queen(Team.WHITE)),
                        Map.entry(new Position(File.f, Rank.THREE), new WhitePawn()),
                        Map.entry(new Position(File.h, Rank.THREE), new WhitePawn()),
                        Map.entry(new Position(File.f, Rank.TWO), new WhitePawn()),
                        Map.entry(new Position(File.g, Rank.TWO), new WhitePawn()),
                        Map.entry(new Position(File.e, Rank.ONE), new Rook(Team.WHITE)),
                        Map.entry(new Position(File.f, Rank.ONE), new King(Team.WHITE))
                )
        );

        Map<Team, Score> score = chessBoard.calculateTotalScore();

        assertThat(score.get(WHITE)).isEqualTo(new Score(19.5));
        assertThat(score.get(BLACK)).isEqualTo(new Score(20));
    }
}
