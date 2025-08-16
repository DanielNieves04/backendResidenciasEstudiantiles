package R.U.R.U.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmailValuesDTO {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;

}
