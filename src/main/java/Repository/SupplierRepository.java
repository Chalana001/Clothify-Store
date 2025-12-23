package Repository;

import modle.entity.CustomerEntity;
import modle.entity.SupplierEntity;

import java.util.List;

public interface SupplierRepository {
    void addSupplier(SupplierEntity supplierEntity);

    SupplierEntity getLastSupplierId();

    void updateSupplier(SupplierEntity supplierEntity);

    void deleteSupplier(String sId);

    List<SupplierEntity> getAllSupplier();

    SupplierEntity getSupplierById(String text);

    List<SupplierEntity> searchSupplierByName(String newText);
}
