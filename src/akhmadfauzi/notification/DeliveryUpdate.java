package akhmadfauzi.notification;

// RefinedAbstraction (Bridge)
public class DeliveryUpdate extends Notification {
    private final String idPsn;
    private final String status;

    public DeliveryUpdate(MessageSender pengirim, String tujuan, String idPsn, String status) {
        super(pengirim, tujuan);
        this.idPsn = idPsn;
        this.status = status;
    }

    @Override
    public void kirim() {
        kirimPesan("Pesanan " + idPsn + " status: " + status + ".");
    }
}
