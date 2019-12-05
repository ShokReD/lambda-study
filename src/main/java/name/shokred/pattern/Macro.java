package name.shokred.pattern;

import java.util.*;

public class Macro {
    private final List<Action> actions = new ArrayList<>();

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }
}
