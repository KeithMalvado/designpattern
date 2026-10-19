package akhmadfauzi.order;

// Subsystem (Facade): penjadwalan pengiriman.
public class DeliveryService {

    public boolean jadwalkan(String idPsn, String alamat) {
        if (alamat == null || alamat.isBlank()) {
            System.out.println("[DeliveryService] Pengiriman " + idPsn + " gagal: alamat kosong.");
            return false;
        }
        System.out.println("[DeliveryService] Pengiriman " + idPsn + " dijadwalkan ke " + alamat);
        return true;
    }
}
