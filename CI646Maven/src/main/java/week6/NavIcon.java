package week6;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NavIcon extends Parent {

    public NavIcon() {
        var bg = new Rectangle(50, 50, null);
        bg.setStrokeWidth(2);
        bg.setStroke(Color.AQUA);
        bg.setArcWidth(15);
        bg.setArcHeight(15);

        getChildren().addAll(bg);
    }
}
