package week3;

public class TerminalModel implements AppModel {
    @Override
    public void speak(String text) {
        System.out.println("Text to speech: " + text);
    }
}
