package ru.job4j.odd.lsp.storage;


import java.util.List;

public class ControlQuality {
    private final Food product;

    public ControlQuality(Food product, List<Store> storeList) {
        this.product = product;
        distribute(storeList);
    }

    public void distribute(List<Store> storeList) {
        for (Store store : storeList) {
            if (store.addCheck(product)) {
                break;
            }
        }
    }
}
