public class OffByN implements CharacterComparator{
    private int n=0;

    public OffByN(int n){
        this.n = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int obo = x-y;
        if (Math.abs(obo) == this.n){
            return true;
        }else {
            return false;
        }
    }
}
