package creatorInvoice.dto.client;

import creatorInvoice.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AddClientDto {

    private Long id;
    private String api_token;
    private Client client;
}
