package name.shokred.pattern.command;

import java.util.*;

public class Macro {
    private final List<Action> actions = new ArrayList<>();

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

    public static void main(String[] args) {
        Macro macro = new Macro();
        Editor editor = new Editor() {
            @Override
            public void save() {
                System.out.println("save");
            }

            @Override
            public void open() {
                System.out.println("open");
            }

            @Override
            public void close() {
                System.out.println("close");
            }
        };

        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }
}
