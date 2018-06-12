package com.bidding.repository;

import com.bidding.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAll();

    List<Request> findByType(int type);

    @Query("select r from Request r where r.product like ?1 and r.price = ?2 and r.itemsCount = ?3 and r.type = ?4")
    List<Request> findQuery(String product, int price, int itemsCount, int type);

    @Query("select r from Request r where r.requester.id = ?1")
    List<Request> findByUserId(Long userId);

}
