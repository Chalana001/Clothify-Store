package service;

import javafx.collections.ObservableList;
import modle.dto.Supplier;

public interface SupplierService {
    void addSupplier(Supplier supplier);

    String getNewSupplierId();

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Supplier selectedSupplier);

    ObservableList<Supplier> getAllSuppliers();

    Supplier getSupplierById(String text);

    ObservableList<Supplier> searchSupplierByName(String newText);

}
