package week3;

public class Factory {

    public static AppModel newRobotModel() {
        return new RobotModel();
    }

    public static AppModel newOSModel() {
        return new OSModel();
    }
}
