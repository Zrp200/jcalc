import java.math.BigDecimal;

/**
 * Created on 1/10/2015.
 * TODO Comment here
 */
public class JCalcModel {
    private BigDecimal total;

    public JCalcModel(){
        total = new BigDecimal(0);
    }

    public String getTotal() {
        return String.valueOf(total);
    }

    public void setTotal(String n) {
        total = new BigDecimal(n);
    }

    public void add(String n) {
        total = total.add(new BigDecimal(n));
    }

    public void subtract(String n) {
        total = total.subtract(new BigDecimal(n));
    }

    public void multiply(String n) {
        total = total.multiply(new BigDecimal(n));
    }

    public void divide(String n) {
        total = total.divide(new BigDecimal(n),BigDecimal.ROUND_HALF_EVEN);
    }

    public void power(String n) {
        total = total.pow(new BigDecimal(n).intValue());
    }

    public String negate(String n) {
        return String.valueOf(new BigDecimal(n).negate());
    }

}
