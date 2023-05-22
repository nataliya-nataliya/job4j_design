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
        double originalPrice = 50.0;
        Food product = new Food("Milk", beforeNowExpiryDate,
                createdDate, originalPrice, 0.5, originalPrice);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedTrashStore = new Trash();
        expectedTrashStore.add(new Food("Milk", beforeNowExpiryDate,
                createdDate, originalPrice, 0.5, originalPrice));
        Assertions.assertTrue(stores.contains(expectedTrashStore));
    }

    @Test
    public void whenHalfOfExpirationDateIsLeftAndFoodIsDistributedToShop() {
        LocalDate expiryDate = now.plusDays(5);
        LocalDate createdDate = now.minusDays(5);
        double originalPrice = 50.0;
        Food product = new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedShopStore = new Shop();
        expectedShopStore.add(new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice));
        Assertions.assertTrue(stores.contains(expectedShopStore));
    }

    @Test
    public void when20PercentsOfExpirationDateIsLeftAndFoodIsDistributedToShopAndPriceWithDiscount() {
        LocalDate expiryDate = now.plusDays(1);
        LocalDate createdDate = now.minusDays(4);
        double originalPrice = 50.0;
        Food product = new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedShopStore = new Shop();
        expectedShopStore.add(new Food("Milk", expiryDate,
                createdDate, originalPrice / 2, 0.5, originalPrice));
        Assertions.assertTrue(stores.contains(expectedShopStore));
    }

    @Test
    public void when80PercentsOfExpirationDateIsLeftAndFoodIsDistributedToWarehouse() {
        LocalDate expiryDate = now.plusDays(4);
        LocalDate createdDate = now.minusDays(1);
        double originalPrice = 50.0;
        Food product = new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedWarehouseStore = new Warehouse();
        expectedWarehouseStore.add(new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice));
        Assertions.assertTrue(stores.contains(expectedWarehouseStore));
    }

    @Test
    public void whenFoodIsDistributedToShopAndResort() {
        LocalDate expiryDate = now.plusDays(1);
        LocalDate createdDate = now.minusDays(4);
        double originalPrice = 50.0;
        Food product = new Food("Milk", expiryDate,
                createdDate, originalPrice, 0.5, originalPrice);
        ControlQuality controlQuality = new ControlQuality(product, stores);
        Store expectedShopStore = new Shop();
        expectedShopStore.add(new Food("Milk", expiryDate,
                createdDate, originalPrice / 2, 0.5, originalPrice));
        Assertions.assertTrue(stores.contains(expectedShopStore));
        controlQuality.resort(stores);
        Assertions.assertTrue(stores.contains(expectedShopStore));
    }
}