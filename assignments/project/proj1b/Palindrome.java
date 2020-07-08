public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }

        return d;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        for (int i = 0; i < d.size() / 2; i++) {
            char a = d.get(i);
            char b = d.get(d.size() - 1 - i);
            if (a != b) {
                return false;
            }
        }

        return true;
    }
}