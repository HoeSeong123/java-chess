package chess;

import static chess.domain.Turn.WHITE_TURN;

import chess.domain.Command;
import chess.domain.Turn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.util.RetryUtil;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {
    private Turn currentTurn = WHITE_TURN;

    public void run() {
        OutputView.printStartMessage();
        Command command = RetryUtil.read(() -> Command.getStartCommand(InputView.readCommand()));
        ChessBoard chessBoard = ChessBoard.initializeChessBoard();
        while (!command.isEnd()) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
            command = RetryUtil.read(() -> processGame(chessBoard));
        }
    }

    private Command processGame(ChessBoard chessBoard) {
        Command command = Command.getProcessCommand(InputView.readCommand());
        if (command.isMove()) {
            List<String> sourcePosition = InputView.readPosition();
            Position source = new Position(sourcePosition.get(0), sourcePosition.get(1));
            List<String> targetPosition = InputView.readPosition();
            Position target = new Position(targetPosition.get(0), targetPosition.get(1));
            currentTurn = chessBoard.move(source, target, currentTurn);
        }

        return command;
    }
}
