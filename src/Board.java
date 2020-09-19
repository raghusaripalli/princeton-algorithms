public class Board {

    private final int[][] tiles;
    private final int missing;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        int n = tiles.length * tiles.length;
        int total = (n * (n + 1)) / 2;
        for (int[] tile : this.tiles) {
            for (int i : tile) {
                total -= i;
            }
        }
        missing = total;
    }

    public int getMissingNumber() {
        return missing;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tiles.length).append("\n");
        for (int[] tile : this.tiles) {
            for (int i : tile) {
                sb.append(" ").append(i);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0, count = 1; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++, count++) {
                if (this.tiles[i][j] == count)
                    hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y)
            return true;

        if (this.getClass() != y.getClass())
            return false;

        Board that = (Board) y;
        if (this.tiles.length != that.tiles.length)
            return false;

        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = {{1, 4, 2}, {8, 6, 0}, {7, 5, 3}};
        Board board = new Board(tiles);
        System.out.println(board.toString());
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Missing number: " + board.getMissingNumber());
    }
}
