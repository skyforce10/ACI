package DataClassess;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_master")
public class OrderMasterTable {
    @PrimaryKey(autoGenerate = true)
    private int IDorder;
    private String order_number;
    private String order_date;

    public int getIDorder() {
        return IDorder;
    }

    public void setIDorder(int IDorder) {
        this.IDorder = IDorder;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

}
