/**
 * Created with IntelliJ IDEA
 * Description:
 * User: JeffRay
 * Date: 2022.10.25
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            char x = word.charAt(i++);
            char y = word.charAt(j--);
            if (x != y) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int l = 0, r = word.length() - 1;
        while (l < r) {
            if (!cc.equalChars(word.charAt(l++), word.charAt(r--))) {
                return false;
            }
        }
        return true;
    }
}
