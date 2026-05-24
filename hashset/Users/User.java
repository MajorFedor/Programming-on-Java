package hashset.Users;

import java.time.LocalDateTime;
import java.util.Objects;

public class User{
    private Integer id;
    private String name;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;
    private static Integer maxId = 1;

    public User(Integer id, String name, String password){
        if(id > maxId){
            this.id = id;
            maxId = id;
        }
        
        this.name = name;
        this.password = password;
        isLoggedIn = false;
    }

    public User (String name, String password){
        this.id = ++maxId;
        this.name = name;
        this.password = password;
        isLoggedIn = false;
    }

    public User(){
        this.id = 0;
        this.name = "name";
        this.password = "password";
        isLoggedIn = false;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    public Integer getMaxId() {
        return maxId;
    }
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public static void setMaxId(Integer newMaxId) {
        if(newMaxId > maxId){
            maxId = newMaxId;
        }
    }

    public void setName(String newName) {
        if(newName != null){
            if(!this.name.equals(newName)){
                        this.name = newName;
            } else {
                System.out.println("You cannot use the same password.");
            }
        }
    }

    public void setPassword(String newPassword) {
        if(newPassword != null){
            if(!this.password.equals(newPassword)){
                        this.password = newPassword;
            } else {
                System.out.println("You cannot use the same password.");
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", isLoggedIn='" + isLoggedIn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if( o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, id);
    }
}
