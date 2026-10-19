package akhmadfauzi.menu;

import java.util.ArrayList;
import java.util.List;

// Composite: paket bundling dengan potongan harga.
public class BundlePackage extends MenuComponent {
    private final List<MenuComponent> isi = new ArrayList<>();
    private final double diskon;

    public BundlePackage(String nm, double diskon) {
        super(nm);
        if (diskon < 0 || diskon > 1) {
            throw new IllegalArgumentException("Diskon harus di antara 0 dan 1");
        }
        this.diskon = diskon;
    }

    public void tambah(MenuComponent komp) {
        isi.add(komp);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (MenuComponent komp : isi) {
            total += komp.getPrice();
        }
        return total * (1 - diskon);
    }

    @Override
    public void print(int level) {
        System.out.println(indentasi(level) + "[Paket] " + nm
                + " (diskon " + Math.round(diskon * 100) + "%)");
        for (MenuComponent komp : isi) {
            komp.print(level + 1);
        }
        System.out.println(indentasi(level) + "  Harga paket: Rp" + rupiah(getPrice()));
    }
}
