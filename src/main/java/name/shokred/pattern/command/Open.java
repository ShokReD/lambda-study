package name.shokred.pattern.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Open implements Action {

    private final Editor editor;

    @Override
    public void perform() {
        editor.open();
    }
}
