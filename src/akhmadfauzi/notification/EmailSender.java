package akhmadfauzi.notification;

// ConcreteImplementor (Bridge)
public class EmailSender implements MessageSender {
    @Override
    public void kirim(String tujuan, String isiPesan) {
        System.out.println("[Email] ke " + tujuan + ": " + isiPesan);
    }
}
