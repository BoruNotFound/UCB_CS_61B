public class OffByN implements CharacterComparator {

    int N;

    public OffByN(int N) {
        this.N = N;
    }

    /** Returns true if the characters are different by exactly one. */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == N) || (diff == -N);
    }

    /** Returns true if the word is a palindrome under the new "equal" rules. */
    @Override
    public boolean isPalindrome(String word) {
        for (int i = 0; i < word.length() / 2; i++) {
            char a = word.charAt(i);
            char b = word.charAt(word.length() - 1 - i);
            if (!equalChars(a, b)) {
                return false;
            }
        }

        return true;
    }
}