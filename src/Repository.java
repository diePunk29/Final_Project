import javax.sound.sampled.Line;
import java.awt.*;
import java.util.Observable;
import java.util.Stack;

public class Repository extends Observable {

    private double distance;

    private Repository() {}

    public Repository getInstance() {
        return null;
    }

    Stack<Point> getPoints() {
        return null;
    }

    Stack<Line> getLines() {
        return null;
    }
}
