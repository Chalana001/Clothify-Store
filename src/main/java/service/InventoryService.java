package service;

import javafx.collections.ObservableList;
import modle.dto.TblInventory;

public interface InventoryService {
    ObservableList<TblInventory> getInventoryDetails();
}
