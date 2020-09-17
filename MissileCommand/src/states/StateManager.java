package states;

/**
 * A class used to keep the currentState in check. This holds the memory of
 * which currentState we are in. In addition this class has getters and setters
 * for the state such that one can change states when needed. The variable is
 * static along with the respective getter and setter for availability purposes.
 * 
 * The class is abstract since it will never be instantiated.
 */
public abstract class StateManager {

	private static State currentState;

	public StateManager() {}

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}
}
