package watchApp;

public class WatchFactory {
    public static WatchComponent createWatchComponent(String type) {
        switch (type) {
            case "Watch":
                return new Watch();
            case "Stopwatch":
                return new Stopwatch();
            case "TimerApp":
                return new TimerApp();
            default:
                throw new IllegalArgumentException("Reloj no soportado: " + type);
        }
    }
}