package src;

/**
 * Score tracker observer.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * @param scoreCounter start counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @return current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
      * @param currentScore new score.
     */
    public void setCurrentScore(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * add 5 point for hit block.
     * @param beingHit - the block that was hist
     * @param hitter - this ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * add points to the score.
     * @param num - points to add
     */
    public void addScore(int num) {
        this.currentScore.increase(num);
    }
}
