package DataClassess;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_detail")
public class OrderDetailTable {
    @PrimaryKey(autoGenerate = true)
    private int IDorderdetail;
    private String order_number;
    private String supplier;
    private String itemname;
    private String qty;
    private String customer;

    public int getIDorderdetail() {
        return IDorderdetail;
    }

    public void setIDorderdetail(int IDorderdetail) {
        this.IDorderdetail = IDorderdetail;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
