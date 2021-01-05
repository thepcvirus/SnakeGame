package snake;

import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFrame;

public class StartSnake extends JFrame {

    int windowwidth = 1000;
    int windowheight = 600;

    int gamewidth = 800;
    int gameheight = 400;

    SnakeModel sm;

    int foodx;
    int foody;

    int size = 20;
    int score = 0;

    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;

    boolean startvalue = true;
    boolean pause = false;

    int speed = 100;

    public void play() {

        startvalue = true;

        setResizable(false);
        setLayout(null);
        setVisible(true);//make it visible
        setSize(windowwidth, windowheight);//size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a snake object to start with it's head
        sm = new SnakeModel(10, 10);

        //creates the snake head view
        JLabel snake = new JLabel(">");
        snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
        add(snake);
        snake.setFont(new Font("bold", size, size));

        //creates the snake body view to follow the head but it isn't created as objects yet
        JLabel snakebody[] = new JLabel[sm.maxnumberofblocks];
        for (int i = 0; i < sm.maxnumberofblocks; i++) {
            snakebody[i] = new JLabel("o");
            add(snakebody[i]);
        }

        //create random numbers for the food location between the game borders 
        Random ran = new Random();
        foodx = (1 + ran.nextInt(gamewidth / 10 - 2)) * 10;
        foody = (1 + ran.nextInt(gameheight / 10 - 2)) * 10;
        JLabel food = new JLabel("+");
        food.setBounds(foodx, foody, size, size);
        add(food);
        food.setFont(new Font("bold", size, size));

        //create score label and display it in te right
        JLabel myscore = new JLabel("SCORE " + score);
        myscore.setBounds(windowwidth - 120, 60, 1000, 50);
        myscore.setFont(new Font("bold", 15, 15));
        add(myscore);

        //display the location of the food to make it easier for the player to find it
        JLabel foodlocation = new JLabel("food location is  " + foodx + ":" + foody);
        foodlocation.setBounds(windowwidth - 170, 100, 170, 50);
        foodlocation.setFont(new Font("bold", 15, 15));
        add(foodlocation);

        //display the snake head locations to make it easier for the player to compare it with the food location
        JLabel snakelocation = new JLabel("snake location is  " + sm.head.getxlocation() + ":" + sm.head.getylocation());
        snakelocation.setBounds(windowwidth - 170, 160, 170, 50);
        snakelocation.setFont(new Font("bold", 15, 15));
        add(snakelocation);

        //create right and bottom border for the game and they are inside the java window
        JButton rightborder = new JButton("");
        rightborder.setBounds(gamewidth, 0, 20, gameheight);
        add(rightborder);
        JButton buttomborder = new JButton("");
        buttomborder.setBounds(0, gameheight, gamewidth + 20, 20);
        add(buttomborder);

        //exit button to exit the game
        JButton exit = new JButton("exit");
        exit.setBounds(420, windowheight - 180, 200, 150);
        exit.setFont(new Font("bold", 50, 50));
        add(exit);
        exit.addActionListener(e -> {
            startvalue = false;
            dispose();
        });

        //left button to move the snake to the left side
        JButton left = new JButton("left");
        left.setBounds(windowwidth - 300, windowheight - 100, 100, 75);
        left.setFont(new Font("bold", 28, 28));
        add(left);
        left.addActionListener(e -> {
            this.left = true;
            right = false;
            up = false;
            down = false;
            snake.setText("<");
            snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
        });

        //right button to move the snake to the right side
        JButton right = new JButton("right");
        right.setBounds(windowwidth - 100, windowheight - 100, 100, 75);
        right.setFont(new Font("bold", 28, 28));
        add(right);
        right.addActionListener(e -> {
            this.left = false;
            this.right = true;
            up = false;
            down = false;
            snake.setText(">");
            snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
        });

        //up button to move the snake to the up side
        JButton up = new JButton("up");
        up.setBounds(windowwidth - 200, windowheight - 175, 100, 75);
        up.setFont(new Font("bold", 28, 28));
        add(up);
        up.addActionListener(e -> {
            this.left = false;
            this.right = false;
            this.up = true;
            down = false;
            snake.setText("^");
            snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
        });

        //down button to move the snake to the down side
        JButton down = new JButton("down");
        down.setBounds(windowwidth - 200, windowheight - 100, 100, 75);
        down.setFont(new Font("bold", 28, 28));
        add(down);
        down.addActionListener(e -> {
            this.left = false;
            this.right = false;
            this.up = false;
            this.down = true;
            snake.setText("v");
            snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
        });

        //pause button to pause the game for some time
        JButton pause = new JButton("pause");
        pause.setBounds(220, windowheight - 180, 200, 150);
        pause.setFont(new Font("bold", 50, 50));
        add(pause);
        pause.addActionListener(e -> {
            if (this.pause) {
                this.pause = false;
            } else {
                this.pause = true;
            }
        });

        //start button to start the game from the beginning
        JButton start = new JButton("start");
        start.setBounds(0, windowheight - 180, 220, 150);
        start.setFont(new Font("bold", 50, 50));
        add(start);
        start.addActionListener(e -> {
            this.left = false;
            this.right = false;
            this.up = false;
            this.down = false;
            this.pause = false;
            for (int i = sm.numberofblocks - 1; i > 0; i--) {
                snakebody[i].setBounds(0, 0, 0, 0);
            }
            score = 0;
            sm.head.updatelocations(10, 10);
            foodx = (1 + ran.nextInt(gamewidth / 10 - 2)) * 10;
            foody = (1 + ran.nextInt(gameheight / 10 - 2)) * 10;
            food.setBounds(foodx, foody, size, size);
            myscore.setText("SCORE " + score);
            foodlocation.setText("food location is  " + foodx + " " + foody);
        });

        //this infinity loop is to make the game updated all the time and to check all variables every time and update their locations to make it look like the player is playing in the realtime
        while (startvalue) {

            //this part moves the snake head to the direction that the button points to
            if (this.left == true) {
                sm.head.updatelocations(sm.head.getxlocation() - 10, sm.head.getylocation());
            } else if (this.right == true) {
                sm.head.updatelocations(sm.head.getxlocation() + 10, sm.head.getylocation());
            } else if (this.up == true) {
                sm.head.updatelocations(sm.head.getxlocation(), sm.head.getylocation() - 10);
            } else if (this.down == true) {
                sm.head.updatelocations(sm.head.getxlocation(), sm.head.getylocation() + 10);
            } else {
                //this condition occures at the start of the game when the snake is stopped
            }

            //this part will be activated when the snake eat the food
            if (sm.head.getxlocation() == foodx && sm.head.getylocation() == foody) {
                score += 10;
                //add new block to the snake body
                sm.addblock();
                //change the position of the food and update the score 
                foodx = (1 + ran.nextInt(gamewidth / 10 - 2)) * 10;
                foody = (1 + ran.nextInt(gameheight / 10 - 2)) * 10;
                food.setBounds(foodx, foody, size, size);
                myscore.setText("SCORE " + score);
                foodlocation.setText("food location is  " + foodx + " " + foody);
            }

            //this part to make  the game endless so when the snake hit the bars yu won't lose but you will be respawn from the other side of the screen
            if (sm.head.getylocation() <= 0) {
                sm.head.updatelocations(sm.head.getxlocation(), gameheight - 10);
            } else if (sm.head.getylocation() >= gameheight) {
                sm.head.updatelocations(sm.head.getxlocation(), 10);
            } else if (sm.head.getxlocation() <= 0) {
                sm.head.updatelocations(gamewidth - 10, sm.head.getylocation());
            } else if (sm.head.getxlocation() >= gamewidth) {
                sm.head.updatelocations(10, sm.head.getylocation());
            }

            //this part activate snake head movement when the game isn't paused
            if (!this.pause) {
                sm.head.move();
                snake.setBounds(sm.head.getxlocation(), sm.head.getylocation(), size, size);
                snakelocation.setText("snake location is  " + sm.head.getxlocation() + " " + sm.head.getylocation());
            }

            //this part remove snake blocks when he eates him self but it's main goal is to update the blocks location
            for (int i = 0; i < sm.numberofblocks && !this.pause; i++) {
                if (sm.body[i].getnextxlocation() == sm.head.getxlocation() && sm.body[i].getnextylocation() == sm.head.getylocation()) {
                    for (int j = i; j < sm.numberofblocks; j++) {
                        snakebody[j].setBounds(0, 0, 0, 0);
                        this.score -= 10;
                    }
                    sm.removeblocks(i);
                    myscore.setText("SCORE " + score);
                }
                if (i == 0) {
                    sm.body[i].updatelocations(sm.head.getxlocation(), sm.head.getylocation());
                } else {
                    sm.body[i].updatelocations(sm.body[i - 1].getxlocation(), sm.body[i - 1].getylocation());
                }
                //snakebody[i].setBounds(sm.body[i].getxlocation(), sm.body[i].getylocation(), size, size);
            }

            //this part is to move the snake blocks and display movement on the screen
            for (int i = 0; i < sm.numberofblocks && !this.pause; i++) {
                sm.body[i].move();
                snakebody[i].setBounds(sm.body[i].getxlocation(), sm.body[i].getylocation(), size, size);
            }
            try {
                //this sleep the snake for 1 second after each iteration we can use it as the speed of the snake 
                sleep(speed);
            } catch (Exception ex) {
                System.out.println("" + ex);
            }
            
            //display the current head location in the terminal every iteration
            System.out.println("head x" + sm.head.getxlocation() + " head y" + sm.head.getylocation());
        }
        //online active when we exit the game
        System.out.println("out of the loop ");

    }

}
