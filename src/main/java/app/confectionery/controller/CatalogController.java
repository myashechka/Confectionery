package app.confectionery.controller;

import app.confectionery.entity.CartRecord;
import app.confectionery.entity.Product;
import app.confectionery.entity.User;
import app.confectionery.service.CartRecordService;
import app.confectionery.service.ProductService;
import app.confectionery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final ProductService productService;
    private final CartRecordService cartRecordService;

    @GetMapping("catalog")
    public String catalog(Model model, Authentication authentication) {
        List<Product> products = productService.getAllByVisibleProduct(true);
        model.addAttribute("products", products);
        model.addAttribute("cartRecord", new CartRecord());
        if (authentication != null) {
            model.addAttribute("user", (User) authentication.getPrincipal());
        }
        return  "catalog";
    }

    @GetMapping("addCartRec")
    public String addCartRec(@RequestParam Integer productId, @RequestParam Integer amountProduct, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Product product = productService.getProduct(productId);
        List<CartRecord> userCartRecords = cartRecordService.findAllCartRecordsById(user.getId());
        for (CartRecord userCartRecord : userCartRecords) {
            if (Objects.equals(userCartRecord.getProduct(), product)) {
                userCartRecord.setAmountProduct(userCartRecord.getAmountProduct() + amountProduct);
                cartRecordService.addCartRecord(userCartRecord);
                return "redirect:/catalog";
            }
        }
        cartRecordService.addCartRecord(user.getId(), product, amountProduct);
        return "redirect:/catalog";
    }
}
