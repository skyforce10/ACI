package DataClassess;

import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;

@Dao
public interface OrderDetailTableDao {
    @Insert
    void insertOrderDetail(OrderDetailTable OrderDetailObj);

    @Insert
    void insertOrderDetailAll(List<OrderDetailTable> OrderDetailList);
}
