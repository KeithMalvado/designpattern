package akhmadfauzi.payment;

// Adaptee (Adapter): layanan baru dengan kontrak berbeda.
public class NewPayService {

    public boolean pay(String userId, double amount, String currency) {
        if (userId == null || userId.startsWith("BLOCK") || amount <= 0 || !"IDR".equals(currency)) {
            System.out.println("[NewPayService] Pembayaran GAGAL untuk user " + userId + " (" + currency + ")");
            return false;
        }
        System.out.printf("[NewPayService] Pembayaran BERHASIL: %.0f %s dari %s%n", amount, currency, userId);
        return true;
    }
}
