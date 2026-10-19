package akhmadfauzi;

import akhmadfauzi.menu.BundlePackage;
import akhmadfauzi.menu.MenuCategory;
import akhmadfauzi.menu.MenuItem;
import akhmadfauzi.notification.DeliveryUpdate;
import akhmadfauzi.notification.EmailSender;
import akhmadfauzi.notification.NotificationService;
import akhmadfauzi.notification.OrderConfirmation;
import akhmadfauzi.notification.PromoNotification;
import akhmadfauzi.notification.PushSender;
import akhmadfauzi.notification.SmsSender;
import akhmadfauzi.notification.WhatsAppSender;
import akhmadfauzi.order.DeliveryService;
import akhmadfauzi.order.InventoryService;
import akhmadfauzi.order.OrderFacade;
import akhmadfauzi.payment.NewPayAdapter;
import akhmadfauzi.payment.NewPayService;
import akhmadfauzi.payment.OldPayAdapter;
import akhmadfauzi.payment.OldPayGateway;
import akhmadfauzi.payment.PaymentProcessor;
import akhmadfauzi.payment.PaymentService;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Main {

    public static void main(String[] argumen) {
        // ===== Composite =====
        MenuItem nasiGoreng = new MenuItem("Nasi Goreng", 20000);
        MenuItem nasiAyam = new MenuItem("Nasi Ayam", 25000);
        MenuItem gorengan = new MenuItem("Gorengan", 5000);
        MenuItem kopiSusu = new MenuItem("Kopi Susu", 15000);
        MenuItem kopiHitam = new MenuItem("Kopi Hitam", 10000);
        MenuItem esTeh = new MenuItem("Es Teh", 5000);

        MenuCategory nasi = new MenuCategory("Nasi");
        nasi.tambah(nasiGoreng);
        nasi.tambah(nasiAyam);

        MenuCategory goreng = new MenuCategory("Gorengan");
        goreng.tambah(gorengan);

        MenuCategory makanan = new MenuCategory("Makanan");
        makanan.tambah(nasi);
        makanan.tambah(goreng);

        MenuCategory kopi = new MenuCategory("Kopi");
        kopi.tambah(kopiSusu);
        kopi.tambah(kopiHitam);

        MenuCategory teh = new MenuCategory("Teh");
        teh.tambah(esTeh);

        MenuCategory minuman = new MenuCategory("Minuman");
        minuman.tambah(kopi);
        minuman.tambah(teh);

        BundlePackage paketHemat = new BundlePackage("Paket Hemat", 0.10);
        paketHemat.tambah(nasiGoreng);
        paketHemat.tambah(esTeh);
        paketHemat.tambah(gorengan);

        MenuCategory akar = new MenuCategory("Menu KampusFood");
        akar.tambah(makanan);
        akar.tambah(minuman);
        akar.tambah(paketHemat);

        System.out.println("=== STRUKTUR MENU (Composite) ===");
        akar.print(0);
        System.out.println("Subtotal Makanan : Rp" + rupiah(makanan.getPrice()));
        System.out.println("Subtotal Minuman : Rp" + rupiah(minuman.getPrice()));
        System.out.println("Harga Paket Hemat: Rp" + rupiah(paketHemat.getPrice()));

        // ===== Adapter =====
        System.out.println("\n=== PEMBAYARAN (Adapter) ===");
        PaymentProcessor byrLama = new OldPayAdapter(new OldPayGateway());
        PaymentProcessor byrBaru = new NewPayAdapter(new NewPayService());
        System.out.println("OldPayAdapter 27.000 -> " + byrLama.process("pgn-001", 27000));
        System.out.println("NewPayAdapter 27.000 -> " + byrBaru.process("pgn-002", 27000));
        System.out.println("NewPayAdapter akun BLOCK -> " + byrBaru.process("BLOCK-01", 27000));

        // ===== Bridge =====
        System.out.println("\n=== NOTIFIKASI (Bridge) ===");
        new OrderConfirmation(new EmailSender(), "budi@kampus.ac.id", "PSN-001", 55000).kirim();
        new OrderConfirmation(new SmsSender(), "081234567890", "PSN-001", 55000).kirim();
        new PromoNotification(new WhatsAppSender(), "081298765432", "HEMAT10", 0.10).kirim();
        new DeliveryUpdate(new PushSender(), "device-budi", "PSN-001", "dalam perjalanan").kirim();

        // ===== Facade =====
        System.out.println("\n=== CHECKOUT (Facade) ===");
        InventoryService inventaris = new InventoryService();
        inventaris.setStok("Nasi Goreng", 10);
        inventaris.setStok("Nasi Ayam", 3);
        inventaris.setStok("Gorengan", 10);
        inventaris.setStok("Es Teh", 10);
        inventaris.setStok("Kopi Susu", 5);

        PaymentService pembayaran = new PaymentService(byrLama);
        DeliveryService pengiriman = new DeliveryService();
        NotificationService notifikasi = new NotificationService(new EmailSender());

        OrderFacade fasad = new OrderFacade(inventaris, pembayaran, pengiriman, notifikasi);

        Map<MenuItem, Integer> pesanan1 = new LinkedHashMap<>();
        pesanan1.put(nasiGoreng, 2);
        pesanan1.put(esTeh, 2);
        pesanan1.put(gorengan, 1);
        boolean hasil1 = fasad.placeOrder("PSN-101", "pgn-001", "budi@kampus.ac.id", "Gedung A", pesanan1);
        System.out.println("Status pesanan 1: " + (hasil1 ? "BERHASIL" : "GAGAL"));

        Map<MenuItem, Integer> pesanan2 = new LinkedHashMap<>();
        pesanan2.put(nasiAyam, 5);
        boolean hasil2 = fasad.placeOrder("PSN-102", "pgn-002", "sari@kampus.ac.id", "Gedung B", pesanan2);
        System.out.println("Status pesanan 2: " + (hasil2 ? "BERHASIL" : "GAGAL"));

        pembayaran.gantiProsesor(byrBaru);
        Map<MenuItem, Integer> pesanan3 = new LinkedHashMap<>();
        pesanan3.put(kopiSusu, 1);
        boolean hasil3 = fasad.placeOrder("PSN-103", "BLOCK-01", "sari@kampus.ac.id", "Gedung B", pesanan3);
        System.out.println("Status pesanan 3: " + (hasil3 ? "BERHASIL" : "GAGAL"));
    }

    private static String rupiah(double nilai) {
        return String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nilai);
    }
}
