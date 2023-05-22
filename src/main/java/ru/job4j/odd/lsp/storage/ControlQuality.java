package ru.job4j.odd.lsp.storage;


import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final Food product;
    private double priceBeforeDiscount;

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

    /* This method collects food from all stores, cleans the stores, and then redistributes all the food.
    When picking up products from the Shop, the price for the product is set at the original price
    (without a discount)
    */

    public void resort(List<Store> storeList) {
        List<Food> allProductsList = new ArrayList<>();
        for (Store store : storeList) {
            if (store.sizeOfStore() == 0) {
                continue;
            }
            if (store instanceof Shop) {
                ((Shop) store).changePriceBackToOriginalPrice();
            }
            allProductsList.addAll(store.findAll());
            store.clearStore();
        }
        for (Food product : allProductsList) {
            new ControlQuality(product, storeList);
        }
    }
}
