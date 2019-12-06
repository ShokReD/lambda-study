package name.shokred.pattern.observer;

public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We did it!");
        }
    }
}
