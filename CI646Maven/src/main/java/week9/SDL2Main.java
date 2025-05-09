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

public class SDL2Main extends GameApplication {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    protected void initGame() {
        var ffc = new ForeignFunctionCaller(
                List.of(Paths.get("SDL2.dll"))
        );

        ffc.setOnLoaded(() -> {
            ffc.execute(ctx -> {

                // call c++ function
                // int SDL_Init(int)
                // SDL_Window * SDL_CreateWindow(const char *title,
                //                              int x,
                //                              int y,
                //                              int w,
                //                              int h,
                //                              Uint32 flags

                int result1 = (int) ctx.call(
                        "SDL_Init",
                        FunctionDescriptor.of(
                                ValueLayout.JAVA_INT, ValueLayout.JAVA_INT
                        ),
                        0
                );

                System.out.println("Called SDL_Init(), result=" + result1);

                var charArray = ctx.allocateCharArrayFrom("Java Title");

                ctx.call(
                        "SDL_CreateWindow",
                        FunctionDescriptor.of(
                                ValueLayout.ADDRESS,
                                ValueLayout.ADDRESS,
                                ValueLayout.JAVA_INT,
                                ValueLayout.JAVA_INT,
                                ValueLayout.JAVA_INT,
                                ValueLayout.JAVA_INT,
                                ValueLayout.JAVA_INT
                        ),
                        charArray, 100, 100, 800, 600, 0
                );

                System.out.println("Called SDL_CreateWindow");
            });

            // don't unload to keep C++ window open
            //ffc.unload();
        });

        // load .dll c++ library
        ffc.load();
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {

    }
}
