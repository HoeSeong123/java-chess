package chess.domain.dao;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.Pawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import java.util.Arrays;

public enum PieceMapper {
    WHITE_KING("King", "WHITE", new King(WHITE)),
    BLACK_KING("King", "BLACK", new King(BLACK)),
    WHITE_QUEEN("Queen", "WHITE", new Queen(WHITE)),
    BLACK_QUEEN("Queen", "BLACK", new Queen(BLACK)),
    WHITE_ROOK("Rook", "WHITE", new Rook(WHITE)),
    BLACK_ROOK("Rook", "BLACK", new Rook(BLACK)),
    WHITE_BISHOP("Bishop", "WHITE", new Bishop(WHITE)),
    BLACK_BISHOP("Bishop", "BLACK", new Bishop(BLACK)),
    WHITE_KNIGHT("Knight", "WHITE", new Knight(WHITE)),
    BLACK_KNIGHT("Knight", "BLACK", new Knight(BLACK)),
    WHITE_PAWN("Pawn", "WHITE", new WhitePawn()),
    BLACK_PAWN("Pawn", "BLACK", new BlackPawn());

    private String type;
    private String team;
    private Piece piece;

    PieceMapper(String type, String team, Piece piece) {
        this.type = type;
        this.team = team;
        this.piece = piece;
    }

    public static PieceMapper from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.piece.equals(piece))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 기물이 DB에 존재하지 않습니다."));
    }

    public String getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

}
