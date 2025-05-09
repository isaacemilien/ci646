package week7;

import com.almasb.fxgl.core.reflect.ReflectionFunctionCaller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class BitCompiler {

    private ReflectionFunctionCaller rfc = new ReflectionFunctionCaller();

    private Consumer<String> outputArea = input -> {};

    public void setOutputArea(Consumer<String> outputArea) {
        this.outputArea = outputArea;
    }

    public Optional<BitProgram> compile(List<String> codeLines) {
        var program = new BitProgram(rfc);
        rfc.addFunctionCallTarget(program);

        // goes through code and syntactically checks instructions 1 by 1

        for (var line : codeLines) {
            var tokens = line.split(" ");

            var instructionName = tokens[0];

            var args = Arrays.stream(tokens)
                    .skip(1)
                    .toList();

            if (rfc.exists(instructionName, args.size())) {

                // all good, perhaps remove if branch

            } else {
                outputArea.accept(
                        "Syntax Error: " + instructionName
                                + " with " + args.size() + " args does not exist"
                );

                return Optional.empty();
            }
        }

        program.setCode(codeLines);
        return Optional.of(program);
    }
}
