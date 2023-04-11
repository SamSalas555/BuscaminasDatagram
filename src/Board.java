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
        generateClues();
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
    private List<Tile> getTileArround(Tile tile){
        List<Tile> tilesArround = new ArrayList<Tile>();
        for (int i = 0; i<8 ; i++){
            int tmpPosRow = tile.getPosRow();
            int tmpPosCol = tile.getPosCol();
            switch (i){
                case 0: tmpPosRow--;break;
                case 1: tmpPosRow--;tmpPosCol++;break;
                case 2: tmpPosCol++;break;
                case 3: tmpPosRow++;tmpPosCol++;break;
                case 4: tmpPosRow++;break;
                case 5: tmpPosRow++;tmpPosCol--;break;
                case 6: tmpPosCol--;break;
                case 7: tmpPosRow--;tmpPosCol--;break;
            }
            if (tmpPosRow>=0 && tmpPosRow<this.tiles.length
            &&tmpPosCol>=0 && tmpPosCol<this.tiles[0].length){
                tilesArround.add(this.tiles[tmpPosRow][tmpPosCol]);
            }
        }
        return tilesArround;
    }
    private void generateClues(){
        for(Tile tile : tileMined){
            System.out.println(getTileArround(tile));
            for(Tile tileS : getTileArround(tile)){
                System.out.println("(" +tileS.getPosRow()+","+tileS.getPosCol()+")");
                tiles[tileS.getPosRow()][tileS.getPosCol()].incNumMines();
                System.out.println(tiles[tileS.getPosRow()][tileS.getPosCol()].getNumMines());
            }
        }
    }
    private void printBoardCmd(){
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                System.out.print(value.isMina() ? "*" : value.getNumMines()>0?value.getNumMines():"-");
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
