package base.model.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
	
	@NonNull
	private String jwt;
	
	private String tokenType="Bearer";
	
}
