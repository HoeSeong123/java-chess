package chess.domain;

import chess.domain.chessPiece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {
    private final List<Piece> line;

    public Line(List<Piece> line) {
        this.line = line;
    }

    public List<Piece> getLine() {
        return Collections.unmodifiableList(line);
    }

    public Piece getChessPiece(Row row) {
        return line.get(row.getValue());
    }

    public Line update(Row row, Piece chessPiece) {
        List<Piece> line = new ArrayList<>(this.line);
        line.set(row.getValue(), chessPiece);
        return new Line(line);
    }
}