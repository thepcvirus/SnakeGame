package snake;

public class block {

    private int xlocation;
    private int ylocation;
    private int nextxlocation;
    private int nextylocation;

    block(int x, int y) {
        xlocation = x;
        ylocation = y;
    }

    public int getxlocation() {
        return xlocation;
    }

    public int getylocation() {
        return ylocation;
    }
    
    public int getnextxlocation() {
        return nextxlocation;
    }

    public int getnextylocation() {
        return nextylocation;
    }

    public void updatelocations(int x, int y) {
        nextxlocation = x;
        nextylocation = y;
    }
    
    public void move(){
        this.xlocation = this.nextxlocation;
        this.ylocation = this.nextylocation;
    }

}
