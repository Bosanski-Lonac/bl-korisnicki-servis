package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AdminDto {
	@NotEmpty(message = "Username moze ostati prazan")
	private String username;
	@NotEmpty(message = "Password ne moze ostati prazan")
	@Size(min = 5)
	
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
