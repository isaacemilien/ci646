package week3;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.net.http.HttpClientService;

import java.util.HashMap;
import java.util.Map;

public class RobotModel implements AppModel {
    @Override
    public void speak(String text) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "text/plain; charset=UTF-8");
        headers.put("X-Tritium-Auth-Token", "xLF6VuHvPqbWUogdOaD73Q9Ez3rCdM");

        FXGL.getTaskService().runAsync(
                FXGL.getService(HttpClientService.class)
                        .sendPUTRequestTask(
                                "https://rt-0143.robothespian.co.uk/tritium/text_to_speech/say",
                                text,
                                headers
                        )
                        .onSuccess(resp -> {
                            System.out.println(resp.body());
                        })
                        .onFailure(e -> {
                            System.out.println("Error: " + e);
                        })
        );
    }
}
