package akhmadfauzi.notification;

// ConcreteImplementor (Bridge)
public class SmsSender implements MessageSender {
    @Override
    public void kirim(String tujuan, String isiPesan) {
        System.out.println("[SMS] ke " + tujuan + ": " + isiPesan);
    }
}
