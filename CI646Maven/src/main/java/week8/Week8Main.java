package week8;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

// 1 byte = 8 bits
// 2 bytes = 16
// 3 bytes = 24
// 4 bytes = 32
// ....
// int = 32 bits = 4 bytes

// 1 byte = 8 bits = 00 00 00 00
//                 & 11 00 00 00


public class Week8Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {

    }

    @Override
    protected void initUI() {
        var textArea = new TextArea();
        textArea.setPrefSize(500, 500);
        textArea.setFont(Font.font(24));

        var btn = new Button("Compress/Encrypt");
        btn.setOnAction(e -> {

            try {
                byte[] data = textArea.getText().getBytes();

                //byte[] data = Files.readAllBytes(Paths.get("lib-client.jar"));
                //byte[] output = new CompressionService().compress(data);
                byte[] encrypted = new EncryptionService().encrypt(data);

                Files.write(Paths.get("TextFile2.bin"), encrypted);
            } catch (IOException ex) {
                //
            }
        });

        FXGL.addUINode(textArea);
        FXGL.addUINode(btn, 550, 100);
    }

    // serialization
    protected void initUI3() {
        CustomType obj = new CustomType(559933);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(Paths.get("data2.dat").toFile(), obj);

        } catch (IOException e) {
            //
        }

        FXGL.getFileSystemService()
                .writeDataTask(obj, "data1.dat")
                .run();
    }

    protected void initUI2() {
        try {
            byte[] data = Files.readAllBytes(Paths.get("TextFile.txt"));
            // 15 data items
            // 3 -> 4
            // 4x4 = 16 pixels

            int sqrt = (int) Math.ceil(Math.sqrt(data.length));

            int width = sqrt;
            int height = sqrt;

            var image = new WritableImage(width, height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int index = x + y * width;

                    if (index < data.length) {
                        byte b = data[index];

                        // int8, take 3 for r, 3 for g, 2 for blue
                        // rr gg bb aa
                        int red   = (b & 0b00110000) >> 4;
                        int green = (b & 0b00001100) >> 2;
                        int blue  = (b & 0b00000011) >> 0;

                        Color color = Color.rgb(red * 255/3, green * 255/3, blue * 255/3);

                        image.getPixelWriter().setColor(x, y, color);
                    }
                }
            }

            WritableImage image2 = new WritableImage(400, 400);

            for (int y = 0; y < 400; y++) {
                for (int x = 0; x < 400; x++) {
                    image2.getPixelWriter().setColor(x, y, FXGLMath.randomColor());
                }
            }

            FXGL.addUINode(new ImageView(image));
            FXGL.addUINode(new ImageView(image2), 400, 0);
        } catch (IOException e) {
            // ignore
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
