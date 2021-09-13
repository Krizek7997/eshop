package sk.krizan.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Nullable
    private Integer id;

    @NonNull
    private String gender;

    @NonNull
    private String name;

    @Nullable
    private String description;

    @NonNull
    private String size;

    @NonNull
    private Double prize;

    @NonNull
    private Integer amountInStock;

    @NonNull
    private Integer categoryId;

    @NonNull
    private Integer vendorId;

}
