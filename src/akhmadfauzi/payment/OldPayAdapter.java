package akhmadfauzi.payment;

// Adapter: menjembatani PaymentProcessor dengan OldPayGateway.
public class OldPayAdapter implements PaymentProcessor {
    private final OldPayGateway gatewayLama;

    public OldPayAdapter(OldPayGateway gatewayLama) {
        this.gatewayLama = gatewayLama;
    }

    @Override
    public boolean process(String idPgn, double nominalRupiah) {
        long nilaiCents = Math.round(nominalRupiah * 100);
        if (nilaiCents <= 0 || nilaiCents > Integer.MAX_VALUE) {
            System.out.println("[OldPayAdapter] Nominal tidak valid: Rp" + nominalRupiah);
            return false;
        }
        return gatewayLama.makePayment(idPgn, (int) nilaiCents);
    }
}
