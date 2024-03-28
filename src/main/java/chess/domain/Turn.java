package chess.domain;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Team;

public enum Turn {
    WHITE_TURN,
    BLACK_TURN;

    public Turn changeTurn() {
        if(this == WHITE_TURN) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }

    public void checkValidMove(Piece piece) {
        Team team = piece.getTeam();
        if(this == WHITE_TURN && team == BLACK) {
            throw new IllegalArgumentException("백팀이 움직일 차례입니다.");
        }
        if(this == BLACK_TURN && team == WHITE) {
            throw new IllegalArgumentException("흑팀이 움직일 차례입니다.");
        }
    }
}
