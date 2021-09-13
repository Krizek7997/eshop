package sk.krizan.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Nullable
    private Integer id;

    @NonNull
    private String gender;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String address;

    @NonNull
    private String postcode;

    @NonNull
    private String phoneNumber;

}
