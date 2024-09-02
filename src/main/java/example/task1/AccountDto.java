package example.task1;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private int id;
    private String description;
    private int amount;
    private String record_date;
}
