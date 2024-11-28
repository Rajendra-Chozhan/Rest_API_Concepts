package utilities;

import com.google.gson.annotations.SerializedName;

public class userModel {
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("last_name")
	private String last_name;
	
	
	@SerializedName("first_name")
	private String first_name;
	
	
	@SerializedName("email")
	private String email;
	
	
	@SerializedName("avatar")
	private String avatar;
	
	
	public String getId() {
		
		return id;
		
	}
	
	
	public void setId(String Id) {
		this.id = id;
		
		
		
	}
}
