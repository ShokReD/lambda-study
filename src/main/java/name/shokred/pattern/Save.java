package name.shokred.pattern;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Save implements Action {

    private final Editor editor;

    @Override
    public void perform() {
        editor.save();
    }
}
