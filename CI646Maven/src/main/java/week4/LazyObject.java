package week4;

import java.util.function.Supplier;

public class LazyObject<T> {

    private T value = null;
    private Supplier<T> supplier;

    public LazyObject(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T getValue() {
        if (value == null) {
            value = supplier.get();
        }

        return value;
    }
}
