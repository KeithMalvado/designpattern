package akhmadfauzi.notification;

// Abstraction (Bridge): menyimpan referensi ke Implementor.
public abstract class Notification {
    protected MessageSender pengirim;
    protected String tujuan;

    public Notification(MessageSender pengirim, String tujuan) {
        this.pengirim = pengirim;
        this.tujuan = tujuan;
    }

    public abstract void kirim();

    protected void kirimPesan(String isiPesan) {
        pengirim.kirim(tujuan, isiPesan);
    }
}
