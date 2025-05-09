package week7;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.reflect.ForeignFunctionCaller;
import com.almasb.fxgl.core.reflect.ReflectionFunctionCaller;
import com.almasb.fxgl.core.reflect.ReflectionUtils;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.util.Arrays;

/**
 * The app for the Bit language.
 */
public class Week7LanguageApp extends GameApplication {

    private BitCompiler compiler = new BitCompiler();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(800);
    }

    @Override
    protected void initUI() {
        var input = new TextArea();
        input.setPrefSize(800, 400);
        input.setFont(Font.font(24));

        var output = new TextArea();
        output.setPrefSize(800, 400);
        output.setFont(Font.font(24));

        var btnRun = new Button("Compile&Run");
        btnRun.setFont(Font.font(24));
        btnRun.setOnAction(e -> {
            var lines = input.getText().split("\n");

            compiler.setOutputArea(inputValue -> output.appendText(inputValue + "\n"));

            compiler.compile(Arrays.asList(lines)).ifPresent(program -> {
                program.setOutputArea(inputValue -> output.appendText(inputValue + "\n"));
                program.run();
            });
        });

        FXGL.addUINode(input);
        FXGL.addUINode(output, 0, 400);
        FXGL.addUINode(btnRun, 850, 100);
    }

    public static void main(String[] args) {

        // useful for coursework! some ideas for reflection in FXGL
        //ReflectionFunctionCaller;
        //ReflectionUtils;
        //ForeignFunctionCaller;


        launch(args);
    }
}
