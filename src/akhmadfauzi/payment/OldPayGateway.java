package akhmadfauzi.payment;

// Adaptee (Adapter): pustaka lama yang tidak boleh diubah.
public class OldPayGateway {

    public boolean makePayment(String account, int amountInCents) {
        if (account == null || account.startsWith("BLOCK") || amountInCents <= 0) {
            System.out.println("[OldPayGateway] Pembayaran GAGAL untuk akun " + account);
            return false;
        }
        System.out.println("[OldPayGateway] Pembayaran BERHASIL: " + amountInCents + " cents dari " + account);
        return true;
    }
}
