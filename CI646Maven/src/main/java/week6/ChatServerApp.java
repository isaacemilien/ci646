package week6;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ChatServerApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {

    }

    @Override
    protected void initUI() {
        // networking

        var server = FXGL.getNetService()
                .newTCPServer(50000);

        server.setOnConnected(connection -> {
            connection.addMessageHandler((conn, bundle) -> {
                String data = bundle.get("message");

                System.out.println("Received: " + data);

                server.getConnections().forEach(c -> {
                    // should be new bundle
                    c.send(bundle);
                });
            });

            System.out.println("New connection: " + connection);
        });

        server.startAsync();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
