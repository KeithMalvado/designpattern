package akhmadfauzi.payment;

// Target (Adapter): kontrak seragam yang dipakai kode pemesanan.
public interface PaymentProcessor {
    boolean process(String userId, double amountInRupiah);
}
