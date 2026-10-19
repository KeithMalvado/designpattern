package akhmadfauzi.notification;

import java.util.Locale;

// RefinedAbstraction (Bridge)
public class OrderConfirmation extends Notification {
    private final String idPsn;
    private final double total;

    public OrderConfirmation(MessageSender pengirim, String tujuan, String idPsn, double total) {
        super(pengirim, tujuan);
        this.idPsn = idPsn;
        this.total = total;
    }

    @Override
    public void kirim() {
        String nominal = String.format(Locale.forLanguageTag("id-ID"), "%,.0f", total);
        kirimPesan("Pesanan " + idPsn + " dikonfirmasi. Total Rp" + nominal);
    }
}
