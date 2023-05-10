package app.confectionery.repository;

import app.confectionery.entity.CartRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRecordRepository extends JpaRepository<CartRecord, Integer> {
    List<CartRecord> findAllByUserId(Integer userId);
}
