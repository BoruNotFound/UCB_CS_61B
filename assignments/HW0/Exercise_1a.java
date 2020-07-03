public class Exercise_1a {
    public static void main(String[] args) {
        int SIZE = 10;
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