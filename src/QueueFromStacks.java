import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue {
	private Stack<T> head;
	private Stack<T> tail;
	private int size;

	public QueueFromStacks() {
		this.clear();
	}

	@Override
	public void clear() {
		this.head = new Stack<>();
		this.tail = new Stack<>();
		this.size = 0;
	}

	@Override
	public void enqueue(Object item) {
		while (!this.tail.isEmpty()) {
			this.head.push(this.tail.pop());
		}
		this.size++;
		this.tail.push((T) item);
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (this.size == 0)
			throw new NoSuchElementException();
		while (!this.head.isEmpty()) {
			this.tail.push(this.head.pop());
		}
		size--;
		return this.tail.pop();
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (this.size == 0)
			throw new NoSuchElementException();
		if (this.head.size() < 1) {
			this.head.push(this.tail.pop());
		}
		return this.head.firstElement();
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
		for (T t : this.head) {
			if (item == t)
				return true;
		}
		for (T t : this.tail) {
			if (item == t)
				return true;
		}
		return false;
	}

	@Override
	public String debugString() {
		return null;
	}

	@Override
	public String toString() {
		String front = "";
		String back = "";
		for (T h : this.head) {
			front += h + ", ";
		}
		for (T t : this.tail) {
			back = t + ", " + back;
		}
		String output = front + back;
		return "[" + output.substring(0, Math.max(output.length() - 2, 0)) + "]";
	}
}
