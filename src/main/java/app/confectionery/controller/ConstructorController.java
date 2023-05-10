package app.confectionery.controller;

import app.confectionery.entity.Product;
import app.confectionery.entity.User;
import app.confectionery.model.ConstructorDTO;
import app.confectionery.service.CartRecordService;
import app.confectionery.service.ProductService;
import app.confectionery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ConstructorController {
    private final ProductService productService;
    private final CartRecordService cartRecordService;

    @GetMapping("constructor")
    public String getConstructor(Model model, Authentication authentication)
    {
        model.addAttribute("constructorDTO", new ConstructorDTO());
        if (authentication != null) {
            model.addAttribute("user", (User) authentication.getPrincipal());
        }
        return "constructor";
    }

    @PostMapping("createCake")
    public String createCake(@ModelAttribute ConstructorDTO constructorDTO, Authentication authentication)
    {
        String description = constructorDTO.getDescription();
        Product order = new Product(0, constructorDTO.getPrice(),
                false, "Индивидуальный заказ", constructorDTO.getDesign(), description);
        order = productService.addProduct(order);
        User user = (User) authentication.getPrincipal();
        cartRecordService.addCartRecord(user.getId(), order, 1);
        return "redirect:/constructor";
    }

    @PostMapping("constructor")
    public String postConstructor(@ModelAttribute Product product)
    {
        productService.addProduct(product);
        return "constructor";
    }
}
