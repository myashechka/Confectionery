package app.confectionery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructorDTO {
    private Integer weight;
    private Integer number_tiers;
    private  String design;
    private String base;

    private Integer price;

    public String getDescription(){
        return ("Начинка: " + base + "\nКоличество ярусов: " + number_tiers + "\nВес: " + weight + " кг");
    }
}
