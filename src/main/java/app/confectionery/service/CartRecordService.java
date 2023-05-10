package app.confectionery.service;

import app.confectionery.entity.CartRecord;
import app.confectionery.entity.Product;
import app.confectionery.repository.CartRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartRecordService {
    private final CartRecordRepository cartRecordRepository;
    private final ProductService productService;

    public void addCartRecord(Integer userId, Product product , Integer amountProduct){
        CartRecord newCartRecord = new CartRecord(0, userId, product, amountProduct);
        cartRecordRepository.save(newCartRecord);
    }

    public void deleteCartRecord(Integer cartRecordId){
        cartRecordRepository.deleteById(cartRecordId);
    }

    public void deleteAllCartRecords(Integer userId){
        List<CartRecord> cartRecords = cartRecordRepository.findAllByUserId(userId);
        cartRecordRepository.deleteAll(cartRecords);
    }

    public List<CartRecord> findAllCartRecordsById(Integer userId){
        return cartRecordRepository.findAllByUserId(userId);
    }

    public CartRecord findCartRecordById(Integer cartRecordId){
        return cartRecordRepository.findById(cartRecordId).orElseThrow();
    }

    public void addCartRecord(CartRecord cartRecord) {
        cartRecordRepository.save(cartRecord);
    }
}
