package vo.memberVO;

public class Member {
	private String id;
	private String password;
	private String name;
	private String phone;
	private String exp;
	private String position; // common code
	private String joinStatus; // common code
	
	public Member() {}

	public Member(String id, String password, String name, String phone, String exp, String position,
			String joinStatus) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.exp = exp;
		this.position = position;
		this.joinStatus = joinStatus;
	}

	public Member(String id, String password, String name, String phone, String exp, String position) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.exp = exp;
		this.position = position;
	}

	public Member(String id, String password, String name, String phone, String exp) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.exp = exp;
	}

	public Member(String id, String password, String name, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public Member(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}
	
	
}
