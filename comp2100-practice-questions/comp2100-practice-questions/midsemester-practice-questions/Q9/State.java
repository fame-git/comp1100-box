import exceptions.NullCharacterException;
import exceptions.NullKeyEventException;

public interface State {

	default void handle(Character character, Key event) {

		check(character, event);

		this.handleInput(character, event);
	}

	public void handleInput(Character character, Key event);

	default void check(Character character, Key event) {

		// TODO
		// START YOUR CODE
		if (event == null) {
			throw new NullKeyEventException();
		}

		if (character == null) {
			throw new NullCharacterException();
		}
		// END YOUR CODE
	}
}
