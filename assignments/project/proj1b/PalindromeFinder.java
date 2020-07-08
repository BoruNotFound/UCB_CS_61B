/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        int N = 2;
        In in = new In("./words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByN(N);

        int count = 0;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && cc.isPalindrome(word)) {
                System.out.println(word);
                count++;
            }
        }

        System.out.println("There are " + count + " palindromes with diff of " + N +".");
    }
}