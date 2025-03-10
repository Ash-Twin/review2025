package a000_SpringBasics.mapper;

import a000_SpringBasics.dao.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

@Mapper
public interface TransactionMapper {

    @Select("select tx_id, name, timestamp from transaction ")
    List<Transaction> getAllTransactions();

    @Select("select u.name, count(tx.tx_id) " +
            "from  transaction tx join user u " +
            "on u.name = tx.name " +
            "group by u.name")
    List<Transaction> getTransactionsGroupedByName();

}
