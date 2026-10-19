package akhmadfauzi.notification;

// ConcreteImplementor (Bridge)
public class PushSender implements MessageSender {
    @Override
    public void kirim(String tujuan, String isiPesan) {
        System.out.println("[Push] ke " + tujuan + ": " + isiPesan);
    }
}
