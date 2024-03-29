package chess.domain;

public enum GameStatus {
    WHITE_TURN,
    BLACK_TURN,
    GAME_OVER;

    public GameStatus changeTurn() {
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }
}
