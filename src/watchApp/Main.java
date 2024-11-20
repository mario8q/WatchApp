package watchApp;

public class Main {
    public static void main(String[] args) {
        WatchComponent watch = WatchFactory.createWatchComponent("Watch");
        watch.display();

        WatchComponent stopwatch = WatchFactory.createWatchComponent("Stopwatch");
        stopwatch.display();

        WatchComponent timer = WatchFactory.createWatchComponent("TimerApp");
        timer.display();
    }
}

