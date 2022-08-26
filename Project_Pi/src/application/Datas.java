package application;

public class Datas {
	private int User_ID;
	private String User_Name;
	private String User_Email;
	private Boolean User_Active;
	public Datas(int user_ID, String user_Name,String User_Email,Boolean User_Active ) {
		super();
		this.User_ID = user_ID;
		this.User_Name = user_Name;
		this.User_Email= User_Email;
		this.User_Active=User_Active;
		 
	 
	}
	
	public Datas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Email() {
		return User_Email;
	}

	public void setUser_Email(String user_Email) {
		User_Email = user_Email;
	}

	public Boolean getUser_Active() {
		return User_Active;
	}

	public void setUser_Active(Boolean user_Active) {
		User_Active = user_Active;
	}
	
 
	
	 

}


