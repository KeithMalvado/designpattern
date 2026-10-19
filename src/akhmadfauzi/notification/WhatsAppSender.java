package akhmadfauzi.notification;

// ConcreteImplementor (Bridge)
public class WhatsAppSender implements MessageSender {
    @Override
    public void kirim(String tujuan, String isiPesan) {
        System.out.println("[WhatsApp] ke " + tujuan + ": " + isiPesan);
    }
}
