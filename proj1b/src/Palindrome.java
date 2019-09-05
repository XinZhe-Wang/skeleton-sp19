public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque temp = new ArrayDeque();
        for (int i = 0;i<word.length();i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }

    public boolean isPalindrome(String word){
        String word_reverse = new StringBuffer(word).reverse().toString();
        if (word.equals(word_reverse)){
            return true;
        }
        else return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        boolean isequal = false;
        for (int i = 0,j=word.length()-1;i<word.length()/2;i++,j--){
            if (cc.equalChars(word.charAt(i),word.charAt(j))==false){
                isequal = false;
            }else {
                isequal = true;
            }
        }
        return isequal;
    }
}
