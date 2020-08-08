import java.util.Iterator;

public class PositionIterator implements Iterator<Position> {
    private int rowIncrement , colIncrement, size;
    private  Position current;
    //have a look
    public PositionIterator(Position p,int rowIncrement, int colIncrement, int size) {
        this.rowIncrement = rowIncrement;
        this.colIncrement = colIncrement;
        this.size = size;
        //since it has to search the entire array after has next()
        current = new Position(p.row - rowIncrement, p.column - colIncrement);
    }

    @Override
    public boolean hasNext() {
        return current.row + rowIncrement < size && current.column + colIncrement < size;
    }

    @Override
    public Position next() {
        current = new Position(current.row + rowIncrement , current.column + colIncrement);
        return current;
    }
}
