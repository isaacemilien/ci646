package week9;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.reflect.ForeignFunctionCaller;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.ValueLayout;
import java.nio.file.Paths;
import java.util.List;

// how to make 2 languages communicate

// a) API over network
// b) serialization (json, xml, binary) of objects in one, deserialization in another
// c) pre-compile one to be executable by another
// d) downcalling / upcalling

public class Week9Main extends GameApplication {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    protected void initGame() {
        var ffc = new ForeignFunctionCaller(
                List.of(Paths.get("native-lib-test.dll"))
        );

        ffc.setOnLoaded(() -> {
            ffc.execute(ctx -> {

                // call c++ function
                // int testDownCall(int)

                int result = (int) ctx.call(
                        "testDownCall",
                        FunctionDescriptor.of(
                                ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
                        ),
                        9
                );

                System.out.println(result);
            });

            ffc.unload();
        });

        // load .dll c++ library
        ffc.load();
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {

    }
}
