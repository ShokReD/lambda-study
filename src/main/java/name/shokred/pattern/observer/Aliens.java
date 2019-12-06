package name.shokred.pattern.observer;

public class Aliens implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("Они отвлеклись, вторгаемся на землю!");
        }
    }
}
