package akhmadfauzi.payment;

import java.util.Locale;

// Subsystem (Facade): memakai Target Adapter, bukan gateway secara langsung.
public class PaymentService {
    private PaymentProcessor prosesor;

    public PaymentService(PaymentProcessor prosesor) {
        this.prosesor = prosesor;
    }

    public void gantiProsesor(PaymentProcessor prosesorBaru) {
        this.prosesor = prosesorBaru;
    }

    public boolean bayar(String idPgn, double total) {
        String nominal = String.format(Locale.forLanguageTag("id-ID"), "%,.0f", total);
        System.out.println("[PaymentService] Memproses pembayaran Rp" + nominal);
        return prosesor.process(idPgn, total);
    }
}
