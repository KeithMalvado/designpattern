package akhmadfauzi.payment;

// Adapter: menjembatani PaymentProcessor dengan NewPayService.
public class NewPayAdapter implements PaymentProcessor {
    private final NewPayService layananBaru;

    public NewPayAdapter(NewPayService layananBaru) {
        this.layananBaru = layananBaru;
    }

    @Override
    public boolean process(String idPgn, double nominalRupiah) {
        return layananBaru.pay(idPgn, nominalRupiah, "IDR");
    }
}
