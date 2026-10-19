package akhmadfauzi.notification;

// Subsystem (Facade) yang memakai Bridge untuk memilih kanal.
public class NotificationService {
    private final MessageSender pengirim;

    public NotificationService(MessageSender pengirim) {
        this.pengirim = pengirim;
    }

    public void kirimKonfirmasi(String tujuan, String idPsn, double total) {
        new OrderConfirmation(pengirim, tujuan, idPsn, total).kirim();
    }

    public void kirimPromo(String tujuan, String kodePromo, double diskon) {
        new PromoNotification(pengirim, tujuan, kodePromo, diskon).kirim();
    }

    public void kirimUpdate(String tujuan, String idPsn, String status) {
        new DeliveryUpdate(pengirim, tujuan, idPsn, status).kirim();
    }
}
