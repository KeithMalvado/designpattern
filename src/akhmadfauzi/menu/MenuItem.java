package akhmadfauzi.menu;

// Leaf (Composite): tidak punya anak.
public class MenuItem extends MenuComponent {
    private final double hrg;

    public MenuItem(String nm, double hrg) {
        super(nm);
        this.hrg = hrg;
    }

    @Override
    public double getPrice() {
        return hrg;
    }

    @Override
    public void print(int level) {
        System.out.println(indentasi(level) + "- " + nm + " : Rp" + rupiah(hrg));
    }
}
