package name.shokred.pattern.observer;

import java.util.*;

public class Moon {
    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }

    public static void main(String[] args) {
        Moon moon = new Moon();
        moon.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("We did it!");
            }
        });
        moon.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("Они отвлеклись, вторгаемся на землю!");
            }
        });

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }
}
