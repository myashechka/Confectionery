package app.confectionery.controller;

import app.confectionery.entity.CartRecord;
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

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartRecordService cartRecordService;
    @GetMapping("cart")
    public String cart(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<CartRecord> cartRecords = cartRecordService.findAllCartRecordsById(user.getId());
        model.addAttribute("cartRecords", cartRecords);
        model.addAttribute("user", user);
        return "cart";
    }

    @GetMapping("delCartRec")
    public String delCartRec(@RequestParam Integer cartRecordId) {
        cartRecordService.deleteCartRecord(cartRecordId);
        return "redirect:/cart";
    }

    @GetMapping("order")
    public String delAllCartRec(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<CartRecord> cartRecords = cartRecordService.findAllCartRecordsById(user.getId());
        cartRecordService.deleteAllCartRecords(user.getId());
        return "redirect:/cart";
    }
}
