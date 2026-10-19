package akhmadfauzi.order;

import akhmadfauzi.menu.MenuItem;
import akhmadfauzi.notification.NotificationService;
import akhmadfauzi.payment.PaymentService;

import java.util.Locale;
import java.util.Map;

// Facade: satu pintu masuk untuk seluruh alur checkout.
public class OrderFacade {
    private final InventoryService inventaris;
    private final PaymentService pembayaran;
    private final DeliveryService pengiriman;
    private final NotificationService notifikasi;

    public OrderFacade(InventoryService inventaris, PaymentService pembayaran,
                       DeliveryService pengiriman, NotificationService notifikasi) {
        this.inventaris = inventaris;
        this.pembayaran = pembayaran;
        this.pengiriman = pengiriman;
        this.notifikasi = notifikasi;
    }

    public boolean placeOrder(String idPsn, String idPgn, String tujuan,
                              String alamat, Map<MenuItem, Integer> daftarItem) {
        System.out.println("\n[OrderFacade] Mulai pesanan " + idPsn + " oleh " + idPgn);

        if (daftarItem == null || daftarItem.isEmpty()) {
            System.out.println("[OrderFacade] Pesanan " + idPsn + " gagal: daftar item kosong.");
            return false;
        }

        double total = 0;
        for (Map.Entry<MenuItem, Integer> entri : daftarItem.entrySet()) {
            MenuItem menu = entri.getKey();
            Integer jml = entri.getValue();
            if (menu == null || jml == null || jml <= 0) {
                System.out.println("[OrderFacade] Pesanan " + idPsn + " gagal: item atau jumlah tidak valid.");
                return false;
            }
            if (!inventaris.cekStok(menu.getName(), jml)) {
                System.out.println("[OrderFacade] Pesanan " + idPsn + " gagal: stok "
                        + menu.getName() + " tidak cukup.");
                return false;
            }
            total += menu.getPrice() * jml;
        }

        if (!pembayaran.bayar(idPgn, total)) {
            System.out.println("[OrderFacade] Pesanan " + idPsn + " gagal: pembayaran ditolak.");
            return false;
        }

        for (Map.Entry<MenuItem, Integer> entri : daftarItem.entrySet()) {
            inventaris.kurangiStok(entri.getKey().getName(), entri.getValue());
        }

        if (!pengiriman.jadwalkan(idPsn, alamat)) {
            for (Map.Entry<MenuItem, Integer> entri : daftarItem.entrySet()) {
                inventaris.kembalikanStok(entri.getKey().getName(), entri.getValue());
            }
            System.out.println("[OrderFacade] Pesanan " + idPsn + " gagal: "
                    + "pengiriman tidak dapat dijadwalkan, stok dikembalikan.");
            return false;
        }

        notifikasi.kirimUpdate(tujuan, idPsn, "sedang disiapkan");
        notifikasi.kirimKonfirmasi(tujuan, idPsn, total);
        String nominal = String.format(Locale.forLanguageTag("id-ID"), "%,.0f", total);
        System.out.println("[OrderFacade] Pesanan " + idPsn + " selesai. Total Rp" + nominal);
        return true;
    }
}
