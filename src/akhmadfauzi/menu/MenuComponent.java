package akhmadfauzi.menu;

import java.util.Locale;

// Component (Composite): kontrak seragam untuk item, kategori, dan paket.
public abstract class MenuComponent {
    protected String nm;

    public MenuComponent(String nm) {
        this.nm = nm;
    }

    public String getName() {
        return nm;
    }

    public abstract double getPrice();

    public abstract void print(int level);

    protected String indentasi(int level) {
        StringBuilder spasi = new StringBuilder();
        for (int lapis = 0; lapis < level; lapis++) {
            spasi.append("  ");
        }
        return spasi.toString();
    }

    protected String rupiah(double nilai) {
        return String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nilai);
    }
}
