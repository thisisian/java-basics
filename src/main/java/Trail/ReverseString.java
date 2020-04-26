package Trail;

public class ReverseString implements CharSequence {

    public String string;

    public ReverseString(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        return this.string.length();
    }

    @Override
    public char charAt(int i) {
        var len = this.string.length();
        return this.string.charAt((len-1) - i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        var len = this.string.length();
        var sb = new StringBuilder(this.string.subSequence((len-1)-i1, (len-1)-i));
        return (new ReverseString(sb.substring(0)));
    }

    public static void main(String[] args) {
        var v = new ReverseString("Hello, world!");
        System.out.println(v.charAt(0) + " " + v.charAt(12));
        System.out.println(v.subSequence(0, 3));
    }
}
