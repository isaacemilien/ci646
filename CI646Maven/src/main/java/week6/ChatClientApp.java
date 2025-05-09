package week6;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.net.Client;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class ChatClientApp extends GameApplication {

    private Client<Bundle> client;

    private List<String> history = new ArrayList<>();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
    }

    @Override
    protected void initUI() {
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);

        var fieldUser = new TextField();

        var textInput = new TextField();
        textInput.setPrefWidth(800);
        textInput.setFont(Font.font(24));
        textInput.setOnAction(e -> {
            var message = fieldUser.getText() + ": " + textInput.getText();

            var bundle = new Bundle("Data");
            bundle.put("message", message);

            textInput.clear();

            client.broadcast(bundle);
        });

        var chatArea = new TextArea();
        chatArea.setFont(Font.font(24));
        chatArea.setPrefSize(800, 530);

        var fieldSearch = new TextField();
        fieldSearch.setFont(Font.font(16));
        fieldSearch.setPrefWidth(FXGL.getAppWidth() * 0.5);
        fieldSearch.setOnAction(e -> {
            // 1
//            new Thread(() -> {
//                doSearch(fieldSearch.getText(), chatArea);
//            }).start();

            // 2
            FXGL.getExecutor().startAsync(() -> {
                doSearch(fieldSearch.getText(), chatArea);
            });

            // 3
            //new SearchThread(fieldSearch.getText(), chatArea).start();

            // 4
//            Executors.newCachedThreadPool().submit(() -> {
//                doSearch(fieldSearch.getText(), chatArea);
//            });
        });

        var bg = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight());
        bg.setFill(null);
        bg.setStroke(Color.color(0.5, 0.5, 0.5, 0.5));
        bg.setArcWidth(45);
        bg.setArcHeight(45);
        bg.setStrokeWidth(2);

        var navBox = new VBox(10);

        for (int i = 0; i < 5; i++) {
            var icon = new NavIcon();

            navBox.getChildren().addAll(icon);
        }

        FXGL.addUINode(fieldUser, 250, 10);
        FXGL.addUINode(fieldSearch, 400, 10);
        FXGL.addUINode(bg, 90, 55);
        FXGL.addUINode(navBox, 20, 55);
        FXGL.addUINode(chatArea, 400, 80);
        FXGL.addUINode(textInput, 400, 630);

        client = FXGL.getNetService().newTCPClient("localhost", 50000);

        client.setOnConnected(connection -> {
            connection.addMessageHandlerFX((conn, bundle) -> {
                String data = bundle.get("message");

                history.add(data);

                chatArea.appendText(data + "\n");
            });
        });

        client.connectAsync();
    }

    private void doSearch(String searchTerm, TextArea output) {
        SearchResult result = new SearchResult();

        history.stream()
                .filter(line -> line.contains(searchTerm))
                .forEach(line -> {
                    result.data += line + "\n";
                });

        FXGL.getExecutor().startAsyncFX(() -> {
            output.clear();
            output.appendText(result.data);
        });
    }

    private static class SearchResult {
        private String data = "";
    }

    private class SearchThread extends Thread {
        private String searchTerm;
        private TextArea output;

        SearchThread(String searchTerm, TextArea output) {
            this.searchTerm = searchTerm;
            this.output = output;
        }

        @Override
        public void run() {
            doSearch(searchTerm, output);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
