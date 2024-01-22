package DataClassess;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderMasterTableDao {
    @Insert
    void insertOrderMaster(OrderMasterTable OrderMasterObj);

    @Insert
    void insertOrderMasterAll(List<OrderMasterTable> OrderMasterList);

    @Query("select IFNULL(MAX(IDorder) + 1, 1) AS max_order_id from order_master")
    String getOrderMaxNumber();
}
