package week7;

import com.almasb.fxgl.core.reflect.ReflectionFunctionCaller;

import java.util.*;
import java.util.function.Consumer;

public class BitProgram {

    private List<String> code = Collections.emptyList();

    private Map<Integer, Integer> memory = new HashMap<>();

    private int temporaryResult = 0;

    private Consumer<Integer> outputArea = input -> {};

    private ReflectionFunctionCaller rfc;

    public BitProgram(ReflectionFunctionCaller rfc) {
        this.rfc = rfc;
    }

    public void setOutputArea(Consumer<Integer> outputArea) {
        this.outputArea = outputArea;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public void add(int i1, int i2) {
        temporaryResult = i1 + i2;
    }

    public void sub(int i1, int i2) {
        temporaryResult = i1 - i2;
    }

    public void mul(int i1, int i2) {
        temporaryResult = i1 * i2;
    }

    public void div(int i1, int i2) {
        temporaryResult = i1 / i2;
    }

    public void set(int location) {
        memory.put(location, temporaryResult);
    }

    public void out() {
        outputArea.accept(temporaryResult);
    }

    public void run() {
        // goes through code and executes instructions 1 by 1

        for (var line : code) {
            var tokens = line.split(" ");

            var instructionName = tokens[0];

            var args = Arrays.stream(tokens)
                    .skip(1)
                    .map(this::parseArg)
                    .toList();

            rfc.call(instructionName, args);
        }
    }

    private String parseArg(String s) {
        String result = s;
        if (s.startsWith("$")) {
            int value = memory.get(Integer.parseInt(s.replace("$", "")));
            result = value + "";
        }

        return result;
    }
}
