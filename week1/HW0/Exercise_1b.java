public class Exercise_1b {
    public static void main(String[] args) {
        drawTriangle(10);
    }

    public static void drawTriangle(int N) {
        int SIZE = N;
        int row = 0;
        int col = 0;

        while (row < SIZE) {
            col = 0;
            while (col <= row) {
                System.out.print("*");
                col ++;
            }
            System.out.println("");
            row ++;
        }
    }
}