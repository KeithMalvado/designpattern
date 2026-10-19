package akhmadfauzi.menu;

import java.util.ArrayList;
import java.util.List;

// Composite: kategori/subkategori yang menyimpan anak.
public class MenuCategory extends MenuComponent {
    private final List<MenuComponent> anak = new ArrayList<>();

    public MenuCategory(String nm) {
        super(nm);
    }

    public void tambah(MenuComponent komp) {
        anak.add(komp);
    }

    public void hapus(MenuComponent komp) {
        anak.remove(komp);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (MenuComponent komp : anak) {
            total += komp.getPrice();
        }
        return total;
    }

    @Override
    public void print(int level) {
        System.out.println(indentasi(level) + "[Kategori] " + nm);
        for (MenuComponent komp : anak) {
            komp.print(level + 1);
        }
    }
}
