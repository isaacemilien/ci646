package week4;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.intelligence.speechrecog.SpeechRecognitionService;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpeechInApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.addEngineService(SpeechRecognitionService.class);
    }

    @Override
    protected void initUI() {
        TextArea output = new TextArea();
        output.setPrefSize(400, 400);
        output.setFont(Font.font(24));

        Button btn1 = new Button("Btn 1");
        btn1.setOnAction(e ->  {
            System.out.println("Btn 1 pressed");
        });
        btn1.setFont(Font.font(24));

        Button btn2 = new Button("Btn 2");
        btn2.setOnAction(e ->  {
            System.out.println("Btn 2 pressed");
        });
        btn2.setFont(Font.font(24));

        FXGL.addUINode(output, 100, 100);
        FXGL.addUINode(btn1, 700, 100);
        FXGL.addUINode(btn2, 700, 150);

        FXGL.getService(SpeechRecognitionService.class)
                .addInputHandler((input, accuracy) -> {

                    // change to percentage if needed, 20% or 0.2
                    if (accuracy < 0.75) {



                        return;
                    }

                    // code to be called when speech in put is recognised

                    System.out.println(input);
                    System.out.println("Accuracy: " + accuracy);

                    // press button one and then something else

                    if (input.contains("press button one") || input.contains("press button 1")) {
                        btn1.fire();
                    } else if (input.contains("press button two") || input.contains("press button 2")) {
                        btn2.fire();
                    } else {
                        System.out.println("Could not recognise command");
                    }

                    System.out.println();
                });

        FXGL.getService(SpeechRecognitionService.class).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
