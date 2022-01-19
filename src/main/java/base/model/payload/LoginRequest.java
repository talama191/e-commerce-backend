package base.model.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoginRequest {
	@NonNull
	private String usernameOrEmail;
	@NonNull
	private String password;
}
