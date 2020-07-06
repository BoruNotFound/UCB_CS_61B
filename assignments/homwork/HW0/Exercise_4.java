public class Exercise_4 {
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= 0) {
                continue;
            }

            // a[i] is positive and calculate the sum
            int sum = 0;
            for (int j = 0; j <= n; j++){
                if (i + j < a.length) {
                    sum += a[i + j];
                } else {
                    // reach the end of the array
                    break;
                }
            }
            a[i] = sum;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}