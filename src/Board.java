import java.util.ArrayList;
import java.util.List;

public class Board {
    Tile [][] tiles;
    int numRow;
    int numCol;
    int numMines;
    List<Tile> tileMined = new ArrayList<Tile>();

    public Board(int numRow, int numCol, int numMines) {
        this.numRow = numRow;
        this.numCol = numCol;
        this.numMines = numMines;
        initTiles();
    }

    public void initTiles(){
        tiles = new Tile[this.numRow][this.numCol];
        for(int i = 0; i< tiles.length; i++){
            for(int j = 0; j< tiles[i].length; j++){
                tiles[i][j]=new Tile(i,j);
            }
        }
        generateMines();
    }

    private void generateMines(){
        int generatedMines=0;
        while (generatedMines != numMines){
            int posTmpRow = (int)(Math.random()*tiles.length);
            int posTmpCol = (int)(Math.random()*tiles[0].length);
            if (!tiles[posTmpRow][posTmpCol].isMina()){
                tiles[posTmpRow][posTmpCol].setMina(true);
                tileMined.add(tiles[posTmpRow][posTmpCol]);
                generatedMines++;
            }
        }
    }

    private void printBoardCmd(){
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                System.out.print(value.isMina() ? "*" : "#");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args){
        Board testBoard = new Board(9,9,10);
        testBoard.printBoardCmd();
        for (Tile tile : testBoard.tileMined) {
            System.out.print("(" +tile.getPosRow() +","+ tile.getPosCol()+")" + tile.isMina());
        }

    }
}
