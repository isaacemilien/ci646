package week5;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Week5App extends GameApplication {

    private GraphicsContext gS;
    private GraphicsContext gC;
    private GraphicsContext gP;

    private int sX = 0;
    private int sY = 0;

    private boolean isRunningTop = true;
    private int cX = 0;
    private int cY = 0;

    private int pX = 0;
    private int pY = 0;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1800);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        var canvasS = new Canvas(600, 600);
        var canvasC = new Canvas(600, 600);
        var canvasP = new Canvas(600, 600);

        gS = canvasS.getGraphicsContext2D();
        gC = canvasC.getGraphicsContext2D();
        gP = canvasP.getGraphicsContext2D();

        gS.setFill(Color.BLUE);
        gC.setFill(Color.RED);
        gP.setFill(Color.BLACK);

        FXGL.addUINode(canvasS, 0, 0);
        FXGL.addUINode(canvasC, 600, 0);
        FXGL.addUINode(canvasP, 600*2, 0);
    }

    @Override
    protected void onUpdate(double tpf) {
        for (int i = 0; i < 50; i++) {
            drawS();
            drawC();
            drawP();
        }
    }

    private void drawS() {
        gS.fillRect(sX, sY, 1, 1);

        sX++;

        if (sX == 600) {
            sX = 0;
            sY++;

            if (sY == 600) {
                sY = 0;
            }
        }
    }

    private void drawC() {
        if (isRunningTop) {
            gC.fillRect(cX, cY, 1, 1);

        } else {
            gC.fillRect(cX, cY + 300, 1, 1);
            cX++;
        }

        if (cX == 600) {
            cX = 0;
            cY++;

            if (cY == 300) {
                cY = 0;
            }
        }

        isRunningTop = !isRunningTop;
    }

    private void drawP() {
        gP.fillRect(pX, pY, 1, 1);
        gP.fillRect(pX, pY + 300, 1, 1);

        pX++;

        if (pX == 600) {
            pX = 0;
            pY++;

            if (pY == 300) {
                pY = 0;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
