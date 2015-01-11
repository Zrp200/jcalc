/**
 * Created on 1/10/2015.
 * TODO Comment here
 */
public class JCalcModel {
    private int total;

    public JCalcModel(){
        total = 0;
    }

    public String getTotal() {
        return String.valueOf(total);
    }

    public void setTotal(String n) {
        total = Integer.parseInt(n);
    }

    public void add(String n) {
        total += Integer.parseInt(n);
    }

    public void substract(String n) {
        total -= Integer.parseInt(n);
    }

    public void multiply(String n) {
        total *= Integer.parseInt(n);
    }

    public void divide(String n) {
        total /= Integer.parseInt(n);
    }

}
