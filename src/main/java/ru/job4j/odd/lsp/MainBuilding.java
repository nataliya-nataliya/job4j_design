package ru.job4j.odd.lsp;


/* It's a violation of the LSP.
 * ShopBuilding class overrides the
 * enterTheBuilding() method of its superclass, Building, and changes its
 * behavior. Preconditions are strengthened in the subclass
 */
public class MainBuilding {
    public static void main(String[] args) {
        Building shop = new ShopBuilding(1);
        shop.enterTheBuilding();
    }
}
