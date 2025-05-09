package week8;

import java.io.Serializable;

public class CustomType implements Serializable {

    private int value;

    public CustomType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
