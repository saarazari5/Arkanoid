package src.Game;

/**
 * Counter.
 */
public class Counter {

    private int counter;

    /**
     * @param counter c.
     */
    public Counter(int counter) {
        this.counter = Math.max(counter, 0);
    }

    /**
     * const overload.
     */
    public Counter() {
        this(0);
    }

    /**
     * add number to current count.
     * @param number to increase.
     */
    public void increase(int number) {
        counter = counter + number;
        if (counter < 0) {
            counter = 0;
        }
    }

    /**
     * subtract number from current count.
     * @param number to decrease.
     */
    public void decrease(int number) {
        counter = counter - number;
        if (counter < 0) {
            counter = 0;
        }
    }

    /**
     *   get current count.
     * @return counter
     */
    public int getValue() {
        return counter;
    }
}
