package com.bilgehan.envanter.Repository;

import com.bilgehan.envanter.Model.entity.InventoryHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, String> {

    @Query(nativeQuery = true,value = "select * from inventory_history order by created_at desc")
    Page<InventoryHistory> findAllWithPagingOrderByCreatedAtDesc(PageRequest request);

}
