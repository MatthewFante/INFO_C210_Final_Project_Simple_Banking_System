public class Customer {
	private int id;
	private String name;
	
// 	CONSTRUCTOR
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
//	GETTERS
	public int getId() {
		return id;
	}	
	
	public String getName() {
		return name;
	}
	
//	SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
