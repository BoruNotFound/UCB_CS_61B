public class Exercise_2 {
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};

        System.out.print(max(numbers));
    }

    public static int max(int[] array) {
        // assume numbers in array are non-negative
        int maxNum = 0;
        
        // use while loop to implement max function
        int i = 0;
        while (i < array.length) {
            if (array[i] > maxNum)
                maxNum = array[i];
            i ++;
        }

        return maxNum;
    }
}