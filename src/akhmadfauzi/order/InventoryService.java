package akhmadfauzi.order;

import java.util.HashMap;
import java.util.Map;

// Subsystem (Facade): pengecekan dan pengurangan stok.
public class InventoryService {
    private final Map<String, Integer> stok = new HashMap<>();

    public void setStok(String nm, int jml) {
        stok.put(nm, jml);
    }

    public boolean cekStok(String nm, int jml) {
        int tersedia = stok.getOrDefault(nm, 0);
        boolean cukup = tersedia >= jml;
        System.out.println("[InventoryService] " + nm + " x" + jml + " | stok " + tersedia
                + (cukup ? " | cukup" : " | kurang"));
        return cukup;
    }

    public void kurangiStok(String nm, int jml) {
        int sisa = stok.getOrDefault(nm, 0) - jml;
        stok.put(nm, sisa);
        System.out.println("[InventoryService] Stok " + nm + " dikurangi " + jml + ", sisa " + sisa);
    }

    public void kembalikanStok(String nm, int jml) {
        int sisa = stok.getOrDefault(nm, 0) + jml;
        stok.put(nm, sisa);
        System.out.println("[InventoryService] Stok " + nm + " dikembalikan " + jml + ", sisa " + sisa);
    }
}
