
abstract class User {
    
	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int accountNum;
    private static int accountSeed = 10000000;

     public User() {

    }
    
    public User(String email, String password) {
    	this.email = email;
    	this.password = password;
    	this.firstName = " first name unknown";
        this.lastName = "last name unknown";
    	this.accountNum = ++accountSeed;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.accountNum = ++accountSeed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract void login();
    
    public boolean passwordMatch(String anotherPassword) { 
		return password.equals(anotherPassword); 
	}
    
    @Override public boolean equals(Object o) {
		if(o == this) {
             return true;
         } else if (!(o instanceof User)) {
             return false;
         } else {
             User user = (User) o;
             if (this.email.equals(user.email) && this.password.equals(user.password)) {
                 return true;
             } else {
                 return false;
             }
         }
	}
    
    @Override public String toString() {
		String result = "Name: " + firstName + " " + lastName + "\n"
				+ "Email: " + email + "\n"
    		    + "Password: " + password + "\n"
    		    + "Account Number: " +accountNum + "\n";
		return result;
	}


    public int hashCode() {
        String name = email + password;
        int key = 0;
        for (int i = 0; i < name.length(); i++) {
            key += (int) name.charAt(i);
        }
        return key;
    }
}
