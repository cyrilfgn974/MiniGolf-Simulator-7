package interfaces;

import org.newdawn.slick.geom.Vector2f;

public class LinearAnimation {

    private boolean running;
    private final int duration;
    private final Vector2f path;

    private Vector2f distance;


    private int time;

    public LinearAnimation(Vector2f distance, int duration){
        this.running = false;
        this.path = distance;
        this.duration = duration;
        this.distance = new Vector2f();
    }

    public Vector2f distance(){
        return distance;
    }

    public void start(){
        if(time >= duration )
            this.time = 0;
        this.running = true;
    }

    public void update(int delta) {

        if (running) {
            Vector2f tmpBefore = path.copy().scale((float)time/duration);
            time += delta;
            this.distance = path.copy().scale((float)time/duration).sub(tmpBefore);
            if (time > duration) {
                running = false;
                distance = new Vector2f(0,0);
                this.invertAnimation();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    private void invertAnimation(){
        path.negateLocal();
    }
}
