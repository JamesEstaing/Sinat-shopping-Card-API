package sinat.com.sinat_shopping_card.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
// I use this class to return data for Frontend
public class ApiResponse {
    private String message;
    private Object data;
}
