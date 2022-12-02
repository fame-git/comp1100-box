public class StandState implements State {
    private StandState() {}

    private static StandState instance = null;
    public static StandState getInstance() {
        if (instance == null) {
            instance = new StandState();
        }
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event.equals(Key.UP)) {
            character.setState(SuspendState.getInstance());
        }

        if (event.equals(Key.DOWN)) {
            character.setState(SquatState.getInstance());
        }

        if (event.equals(Key.RIGHT)) {
            character.setState(WalkState.getInstance());
        }
    }
}
