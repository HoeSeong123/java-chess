package chess.util;

import chess.view.OutputView;
import java.util.function.Supplier;

public class RetryUtil {
    public static <T> T read(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return read(supplier);
        }
    }
//
//    public static <T> T read(Consumer<T> supplier) {
//        try {
//            return supplier.accept(T);
//        } catch (IllegalArgumentException e) {
//            OutputView.printExceptionMessage(e.getMessage());
//            return read(supplier);
//        }
//    }
}
