abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int accountNum;
    private static int accountSeed = 10000000;

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

    public int hashCode() {
        String name = email + password;
        int key = 0;
        for (int i = 0; i < name.length(); i++) {
            key += (int) name.charAt(i);
        }
        return key;
    }
}
