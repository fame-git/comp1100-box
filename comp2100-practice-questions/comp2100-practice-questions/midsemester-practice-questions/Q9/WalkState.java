public class WalkState implements State {
    private WalkState() {}

    private static WalkState instance = null;
    public static WalkState getInstance() {
        if (instance == null) {
            instance = new WalkState();
        }
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event.equals(Key.LEFT)) {
            character.setState(StandState.getInstance());
        }
    }
}
