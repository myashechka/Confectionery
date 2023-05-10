package app.confectionery.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "cart_records")
public class CartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer amountProduct;
}