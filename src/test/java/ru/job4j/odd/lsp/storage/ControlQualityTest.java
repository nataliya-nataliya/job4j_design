package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class ControlQualityTest {
    Storages storages;
    LocalDate now;
    List<Store> stores;

    @BeforeEach
    public void createStorages() {
        storages = new Storages();
        now = LocalDate.now();
        stores = storages.getStoreList();
    }


    @Test
    public void whenExpirationDateHasExpiredAndFoodIsDistributedToTrash() {
        LocalDate beforeNowExpiryDate = now.minusDays(1);
        LocalDate createdDate = now.minusDays(5);
        Food product = new Food("Milk", beforeNowExpiryDate,
                createdDate, 50.0, 0.5);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedTrashStore = new Trash();
        expectedTrashStore.add(new Food("Milk", beforeNowExpiryDate,
                createdDate, 50.0, 0.5));
        Assertions.assertTrue(stores.contains(expectedTrashStore));
    }

    @Test
    public void whenHalfOfExpirationDateIsLeftAndFoodIsDistributedToShop() {
        LocalDate expiryDate = now.plusDays(5);
        LocalDate createdDate = now.minusDays(5);
        Food product = new Food("Milk", expiryDate,
                createdDate, 50.0, 0.5);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedTrashStore = new Shop();
        expectedTrashStore.add(new Food("Milk", expiryDate,
                createdDate, 50.0, 0.5));
        Assertions.assertTrue(stores.contains(expectedTrashStore));
    }

    @Test
    public void when20PercentsOfExpirationDateIsLeftAndFoodIsDistributedToShopAndPriceWithDiscount() {
        LocalDate expiryDate = now.plusDays(1);
        LocalDate createdDate = now.minusDays(4);
        Food product = new Food("Milk", expiryDate,
                createdDate, 50.0, 0.5);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedTrashStore = new Shop();
        expectedTrashStore.add(new Food("Milk", expiryDate,
                createdDate, 25.0, 0.5));
        Assertions.assertTrue(stores.contains(expectedTrashStore));
    }

    @Test
    public void when80PercentsOfExpirationDateIsLeftAndFoodIsDistributedToWarehouse() {
        LocalDate expiryDate = now.plusDays(4);
        LocalDate createdDate = now.minusDays(1);
        Food product = new Food("Milk", expiryDate,
                createdDate, 50.0, 0.5);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedTrashStore = new Warehouse();
        expectedTrashStore.add(new Food("Milk", expiryDate,
                createdDate, 50.0, 0.5));
        Assertions.assertTrue(stores.contains(expectedTrashStore));
    }
}