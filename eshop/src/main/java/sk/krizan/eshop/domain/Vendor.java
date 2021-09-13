package sk.krizan.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Nullable
    private Integer id;

    @NonNull
    private String name;

    @Nullable
    private String description;

    @NonNull
    private String address;

}
