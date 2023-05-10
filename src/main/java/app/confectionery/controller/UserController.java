package app.confectionery.controller;

import app.confectionery.entity.CartRecord;
import app.confectionery.entity.Product;
import app.confectionery.entity.User;
import app.confectionery.model.ConstructorDTO;
import app.confectionery.service.CartRecordService;
import app.confectionery.service.ProductService;
import app.confectionery.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.extras.springsecurity6.auth.Authorization;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    private final CartRecordService cartRecordService;

    @GetMapping("index")
    public String index(Model model, Authentication authentication)
    {
        if (authentication != null) {
            model.addAttribute("user", (User) authentication.getPrincipal());
        }
        return "index";
    }

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

    @GetMapping("registration")
    public String getRegistration(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("registration")
    public String postRegistration(@ModelAttribute User user, HttpServletRequest req)
    {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String getSignIn(Model model)
    {
        model.addAttribute("user", new User());
        return "sign_in";
    }

    @PostMapping("login")
    public String postSignIn(@ModelAttribute User user, HttpServletRequest req, Model model) {

        userService.authorise(user, req);

        return "redirect:/index";
    }

    @PostMapping("login?logout")
    public String logout() {
        return "redirect:/index";
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
