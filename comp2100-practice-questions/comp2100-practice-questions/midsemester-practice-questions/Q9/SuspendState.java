public class SuspendState implements State {
    private SuspendState() {}

    private static SuspendState instance = null;
    public static SuspendState getInstance() {
        if (instance == null) {
            instance = new SuspendState();
        }
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event.equals(Key.DOWN)) {
            character.setState(StandState.getInstance());
        }
    }
}
