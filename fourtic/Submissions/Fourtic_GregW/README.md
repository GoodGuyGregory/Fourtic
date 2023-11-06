# Fourtic MiniMax

**Greg Witt**

Fourtic is a turn based game which requires two players in an adversarial manner to pick and choose empty spaces within a four by four grid. Similar to tic tac toe. In order to add some intersting elements the game requires players to compete against one another with the intention of filling the grid and then tallying up scores after the board is filled to determine a winner.

## Implementation

The board is easily represented by a **char[][]** a two dimensional char array. This is read into the program with the help of the **FourticReader** class. This class, has a read method which will take the specified path for the file to be read and then iterate through the provided file to detetermine the player at turn, and previously the inital score. I used this to code to test initial scoring methods. These were later used as the basis of the main scoring implementation throughout the main class **Homework**. The Homework class is where **NegaMax** is implemented. Using the scoring method intiially sketched from the **FourticReader** I was able to develope the additional needed methods within the homework class to build off of the previous work I had tested.

**Homework** contains several methods that are unique to the **NegaMax** implementation. **determinePlayerScore** is used to determine the player's score as the play shifts from opponent to opponent. and it's result is returned for the **oppsitePlayerValue** for the iterations within the **availableMoves** within the main negaMax logic. I also undo previous moves to maintain the integrity of the position within the _negaMax_ calls. with the _position[move[0]][move[1]_ all of these are stored within another unique method call **getMoves** which takes the current position of the board and discovers all of the positions of the blank places to allow for a list of arrays with an x, and y coordinate to correspond with the position's move within the board.

unique to Java I had to implement **negaMax** with a _Integer.MIN_VALUE_ this will all the smallest possible value to be compared to in the **Math.max** call to determine to positions worth.

## Running Tests

in order to run the tests provided in the read me. ensure that all of the test files are located within the same directory as the **Homework** and **FourticReader** classes.

this should be provided as such within this submission on Canvas

execute the following within the `main/` folder.

```shell
## current working directory main/
## commands to execute a test of play-7
javac *.java && java Homework ./play-7.txt

```

this should return the results within the terminal.
please don't hestitate to reachout in Zulip if there are any problems.

### Resources

**Documentation**

[MiniMax with Alpha Beta Pruning](https://www.hackerearth.com/blog/developers/minimax-algorithm-alpha-beta-pruning/)

[NegaMax Algorithm Pseudo Code Implentation](https://www.researchgate.net/figure/NegaMax-Algorithm-Pseudo-Code_fig3_262672371)

[GeeksForGeeks MiniMax Algorithm](https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/)

[NegaMax Wiki](https://en.wikipedia.org/wiki/Negamax)

**Videos**

[NegaMax vs MiniMax Overview](https://www.youtube.com/watch?v=6ib1Kf44KR0)
[MiniMax Overview](https://www.youtube.com/watch?v=l-hh51ncgDI)  
[MIT MiniMax Adversarial Algorithm](https://www.youtube.com/watch?v=STjW3eH0Cik&t=372s)  
[John Levin Explaination of Alpha Beta ](https://www.youtube.com/watch?v=zp3VMe0Jpf8&t=308s)  
[Code Train MiniMax Build with Tic Tac Toe](https://www.youtube.com/watch?v=trKjYdBASyQ&t=1357s)

**Configurations**

[Vscode Debugging Args](https://code.visualstudio.com/docs/java/java-debugging)
