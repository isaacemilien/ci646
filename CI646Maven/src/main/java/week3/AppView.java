package week3;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// visual UI
public class AppView extends Pane {

    private AppModel model1 = Factory.newOSModel();
    private AppModel model2 = Factory.newRobotModel();

    public AppView() {
        TextField field = new TextField();
        field.setFont(Font.font(24));

        Button btn1 = new Button("Btn 1");
        btn1.setOnAction(e -> {
            model1.speak(field.getText());
        });

        Button btn2 = new Button("Btn 2");
        btn2.setOnAction(e -> {
            model2.speak(field.getText());
        });

        btn1.setFont(Font.font(24));
        btn2.setFont(Font.font(24));

        field.setTranslateX(100);
        field.setTranslateY(100);

        btn1.setTranslateX(600);
        btn1.setTranslateY(100);

        btn2.setTranslateX(600);
        btn2.setTranslateY(150);

        getChildren().addAll(field, btn1, btn2);
    }
}
