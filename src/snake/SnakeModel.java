package snake;

public class SnakeModel {

    public block head;
    public block body[];
    public int numberofblocks;
    public int maxnumberofblocks;

    public SnakeModel(int startpositionx, int startpositiony) {//creating the head of the snake with start positions
        maxnumberofblocks = 100;
        head = new block(startpositionx, startpositiony);
        head.updatelocations(startpositionx, startpositiony);
        body = new block[maxnumberofblocks];
        numberofblocks = 0;
    }

    public boolean addblock() {
        if (numberofblocks == 0) {
            body[numberofblocks] = new block(head.getnextxlocation(), head.getnextylocation());//connecting the first node to the head
            numberofblocks += 1;//increasing the number of created blocks
            return true;
        } else if (numberofblocks < maxnumberofblocks) {//creating a new node while the snake size is less than it's maximum size
            body[numberofblocks] = new block(body[numberofblocks - 1].getnextxlocation(), body[numberofblocks - 1].getnextylocation());//connecting the new created node with the previous node to it
            numberofblocks += 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeblocks(int deletefrom) {
        for (int i = this.numberofblocks; i > deletefrom; i = this.numberofblocks) {
            if (numberofblocks > 0) {
                body[i] = null;
                numberofblocks -= 1;
            }
        }
        return true;
    }

}
