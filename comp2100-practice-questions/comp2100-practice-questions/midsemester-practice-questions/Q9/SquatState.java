public class SquatState implements State {
    private SquatState() {}

    private static SquatState instance = null;
    public static SquatState getInstance() {
        if (instance == null) {
            instance = new SquatState();
        }
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event.equals(Key.UP)) {
            character.setState(StandState.getInstance());
        }
    }
}
