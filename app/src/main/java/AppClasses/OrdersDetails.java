package AppClasses;

import java.util.List;

public class OrdersDetails {
    private String Supplier;
    private Items items;

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
