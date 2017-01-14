package pong;

// Action on Game | Player
public interface Action<T> {

	public abstract void action(T obj);
	public abstract void revertedAction(T obj);
}
