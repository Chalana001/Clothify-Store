package service;

import Repository.SupplierRepository;
import Repository.SupplierRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.dto.Customer;
import modle.dto.Supplier;
import modle.entity.CustomerEntity;
import modle.entity.SupplierEntity;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
    private ObservableList<Supplier> supplierObservableListForSearchCustomer = FXCollections.observableArrayList();
    private List<SupplierEntity> supplierEntityList = null;

    SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public void addSupplier(Supplier supplier) {
        if (supplier == null){return;}

        supplierRepository.addSupplier(new SupplierEntity(
                supplier.getSId(),
                supplier.getSName(),
                supplier.getSPhoneNumber(),
                supplier.getSEmail()
        ));
    }

    @Override
    public String getNewSupplierId() {
        SupplierEntity lastSupplier = supplierRepository.getLastSupplierId();
        if(lastSupplier==null) {
            return "S001";
        }
        String lastSupId = lastSupplier.getSId();
        int number = Integer.parseInt(lastSupId.split("S")[1]); //1
        number++;
        return String.format("S%03d", number);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        if (supplier == null){
            return;
        }
        supplierRepository.updateSupplier(new SupplierEntity(
                supplier.getSId(),
                supplier.getSName(),
                supplier.getSPhoneNumber(),
                supplier.getSEmail()
        ));
    }

    @Override
    public void deleteSupplier(Supplier selectedSupplier) {
        if (selectedSupplier == null){
            return;
        }
        supplierRepository.deleteSupplier(selectedSupplier.getSId());
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        supplierObservableList.clear();
        supplierEntityList = supplierRepository.getAllSupplier();

        for (SupplierEntity supplierEntity: supplierEntityList){
            supplierObservableList.add(new Supplier(
                    supplierEntity.getSId(),
                    supplierEntity.getSName(),
                    supplierEntity.getSPhoneNumber(),
                    supplierEntity.getSEmail()
            ));
        }
        return supplierObservableList;
    }

    @Override
    public Supplier getSupplierById(String text) {
        SupplierEntity supplierEntity = supplierRepository.getSupplierById(text);
        return new Supplier(
                supplierEntity.getSId(),
                supplierEntity.getSName(),
                supplierEntity.getSPhoneNumber(),
                supplierEntity.getSEmail()
        );
    }

    @Override
    public ObservableList<Supplier> searchSupplierByName(String newText) {
        if (!supplierObservableListForSearchCustomer.isEmpty()){supplierObservableListForSearchCustomer.clear();}

        if(newText == null || newText.trim().length()<2){
            return null;
        }

        supplierEntityList = supplierRepository.searchSupplierByName(newText);
        supplierObservableListForSearchCustomer.clear();
        for (SupplierEntity supplierEntity: supplierEntityList){
            supplierObservableListForSearchCustomer.add(new Supplier(
                    supplierEntity.getSId(),
                    supplierEntity.getSName(),
                    supplierEntity.getSPhoneNumber(),
                    supplierEntity.getSEmail()
            ));
        }
        return supplierObservableListForSearchCustomer;
    }
}
