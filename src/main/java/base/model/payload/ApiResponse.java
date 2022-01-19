package base.model.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Return response to client with success status and message
 * 
 * @author Admin
 *
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ApiResponse {
	@NonNull
	private Boolean success;
	
	@NonNull
	private String message;
	

}
