package AppClasses;

import java.util.List;

public class Orders {
    private String OrderNumber;
    private String Order_date;
    private List<OrdersDetails> ordersDetailsList;

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }

    public List<OrdersDetails> getOrdersDetailsList() {
        return ordersDetailsList;
    }

    public void setOrdersDetailsList(List<OrdersDetails> ordersDetailsList) {
        this.ordersDetailsList = ordersDetailsList;
    }
}
