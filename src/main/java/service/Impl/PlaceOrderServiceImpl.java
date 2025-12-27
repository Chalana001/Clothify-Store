package service.Impl;

import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.Customer;
import modle.dto.Orders;
import modle.dto.Product;
import service.*;


public class PlaceOrderServiceImpl implements PlaceOrderService {

    ProductService productService = new ProductServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();

    @Override
    public Product getProductById(String text) {
        return productService.getProductById(text);
    }

    @Override
    public ObservableList<Product> searchProductByName(String newText) {
        return productService.searchProductByName(newText);
    }

    @Override
    public String genOrderId() {
        String lastOrderId = orderService.getLastOrderId();
        if(lastOrderId==null || lastOrderId.equals("")) {
            return "L001";
        }
        int number = Integer.parseInt(lastOrderId.split("L")[1]); //1
        number++;
        return String.format("L%03d", number);
    }

    @Override
    public void placeOrder(Orders orders, ObservableList<CartProducts> cartProducts) {
        orderService.addOrder(orders);
        orderDetailsService.addOrderDetails(orders, cartProducts);
    }

    @Override
    public ObservableList<Customer> searchCustomerByName(String newText) {
        return customerService.searchCustomerByNameSearch(newText);
    }
}
