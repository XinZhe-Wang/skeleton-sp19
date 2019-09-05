public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int obo = x-y;
        if (Math.abs(obo)==1){
            return true;
        }else {
            return false;
        }
    }
}
