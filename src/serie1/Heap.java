package serie1;
import java.util.Comparator;
import java.util.Objects;


public class Heap<T>{
    private final int INITIAL_SIZE = 10;
    private int size, num_elements = 0;
    private T[] array;
    private Comparator<T> comparator;

    public Heap(Comparator<T> comparator){
        this.comparator = comparator;
        this.size = INITIAL_SIZE;
        this.array = (T[]) new Object[this.size];
    }

    public Heap(Comparator<T> comparator, int size){
        this.comparator = comparator;
        this.size = size;
        this.array = (T[]) new Object[this.size];
    }

    public int size() {
        return this.num_elements;
    }

    public void add(T a) {
        if (num_elements == size){
            resizeArray();
        }
        array[num_elements++] = a;
        heapifyUp(num_elements);
    }

    public T poll() {
        if (num_elements == 0) return null;
        T element = array[0];
        array[0] = array[num_elements - 1];
        array[num_elements - 1] = null;
        num_elements--;
        heapifyDown(1);
        return element;
    }

    public T peek() {
        if (num_elements == 0) return null;
        return array[0];
    }

    public void replaceHead(T head){
        array[0] = head;
        heapifyDown(1);
    }

    public Object[] toArray() {
        Object[] array=  new Object[num_elements];
        if (num_elements > 0)
            System.arraycopy(this.array, 0, array, 0, num_elements);
        return array;
    }

    public void changeComparator(Comparator<T> comparator){
        this.comparator = comparator;
        heapifyFull();
    }

    private void resizeArray() {
        this.size *= 2;
        T[] new_array = (T[]) new Objects[this.size];
        System.arraycopy(array, 0 , new_array, 0, num_elements);
    }

    private void heapifyFull(){
        for (int i = num_elements / 2; i > 0; i--){
            heapifyDown(i);
        }
    }

    private void switchElements(int pos1, int pos2){
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }

    private void heapifyUp(int current_position){
        // Check if root element.
        if (current_position == 1) return;
        int parentPosition = current_position / 2;
        if (comparator.compare(array[current_position - 1], array[parentPosition - 1]) < 0)
            switchElements(current_position - 1, parentPosition - 1);
        heapifyUp(parentPosition);
    }

    private void heapifyDown(int current_position){
        // Check if there is a left child.
        if (current_position * 2 > num_elements) return;
        int child_position;
        if (((current_position * 2) + 1) > num_elements){
            // No right child.
            child_position = current_position * 2;
        } else {
            // Compare children.
            if (comparator.compare(array[current_position * 2 - 1], array[current_position * 2]) <= 0){
                child_position = current_position * 2;
            } else {
                child_position = current_position * 2 + 1;
            }
        }
        if (comparator.compare(array[child_position - 1], array[current_position - 1]) < 0) {
            switchElements(current_position - 1, child_position - 1);
            heapifyDown(child_position);
        }
    }
}
