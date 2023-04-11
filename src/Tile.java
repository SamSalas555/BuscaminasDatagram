public class Tile {
    private int posRow;
    private int posCol;
    private boolean mina;

    private int numMines;

    public Tile(int posRow, int posCol) {
        this.posRow = posRow;
        this.posCol = posCol;
    }

    public int getPosRow() {
        return posRow;
    }

    public void setPosRow(int posRow) {
        this.posRow = posRow;
    }

    public int getPosCol() {
        return posCol;
    }

    public void setPosCol(int posCol) {
        this.posCol = posCol;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public int getNumMines() {
        return numMines;
    }

    public void incNumMines() {
        this.numMines++;
    }
}
