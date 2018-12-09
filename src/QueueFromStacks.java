import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class QueueFromStacks<T> implements SimpleQueue {
	private static final int CAPACITY = 5;

	public  T[] array;
	private int head;
	private int size;
	private int capacity;

	public QueueFromStacks() {
		this.capacity = CAPACITY;
		this.clear();
	}

	@Override
	public void clear() {
		this.array = (T[]) Array.newInstance(Object.class, this.capacity);
		this.head = 0;
		this.size = 0;
	}

	@Override
	public void enqueue(Object item) {
		if (this.head + this.size == this.capacity) {
			this.capacity *= 2;
			T[] resized = (T[]) Array.newInstance(Object.class, this.capacity);
			for (int i = 0; i < this.size; i++) {
				resized[i] = this.array[this.head + i];
			}
			this.array = resized;
			this.head = 0;
		}
		this.array[this.head + this.size] = (T) item;
		this.size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (this.array[this.head] == null)
			throw new NoSuchElementException();
		this.size--;
		return this.array[this.head++];
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (this.array[this.head] == null)
			throw new NoSuchElementException();
		return this.array[this.head];
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean contains(Object item) {
		for (int i = 0; i < this.size; i++) {
			if (this.array[this.head + i] == item)
				return true;
		}
		return false;
	}

	@Override
	public String debugString() {
		String output = "";
		for (int i = 0; i < this.size; i++) {
			output += this.array[this.head + i].toString() ;
		}
		return output;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < this.size; i++) {
			output += this.array[this.head + i].toString() + ", ";
		}
		return "[" + output.substring(0, Math.max(output.length()-2, 0)) + "]";
	}
}
