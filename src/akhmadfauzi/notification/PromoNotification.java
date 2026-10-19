package akhmadfauzi.notification;

// RefinedAbstraction (Bridge)
public class PromoNotification extends Notification {
    private final String kodePromo;
    private final double diskon;

    public PromoNotification(MessageSender pengirim, String tujuan, String kodePromo, double diskon) {
        super(pengirim, tujuan);
        this.kodePromo = kodePromo;
        this.diskon = diskon;
    }

    @Override
    public void kirim() {
        kirimPesan("Promo " + kodePromo + " diskon " + Math.round(diskon * 100) + "% untuk pesanan berikutnya.");
    }
}
