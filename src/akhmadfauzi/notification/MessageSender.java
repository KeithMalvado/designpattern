package akhmadfauzi.notification;

// Implementor (Bridge): kanal pengiriman pesan.
public interface MessageSender {
    void kirim(String tujuan, String isiPesan);
}
