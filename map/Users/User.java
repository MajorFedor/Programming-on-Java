package map.Users;

import java.time.LocalDateTime;
import java.util.Objects;

import map.UserIdentifire.UserIdentifire;

public class User{
    private UserIdentifire userIdentifire;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;
    private static Integer maxId = 1;

    public User(Integer id, String name, String password){
        if(id > maxId){
            maxId = id;
        }
        
        this.userIdentifire = new UserIdentifire(id,name);
        this.password = password;
        isLoggedIn = false;
    }

    public User (String name, String password){
        this.userIdentifire = new UserIdentifire(++maxId,name);
        this.password = password;
        isLoggedIn = false;
    }

    public User(){
        this.userIdentifire = new UserIdentifire(0, "name");
        this.password = "password";
        isLoggedIn = false;
    }

    public UserIdentifire getUserIdentifire(){
        return userIdentifire;
    }

    public Integer getId() {
        return userIdentifire.getId();
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
        return userIdentifire.getName();
    }

    public String getPassword() {
        return password;
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
            if(!this.userIdentifire.getName().equals(newName)){
                        this.userIdentifire.setName(newName);
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
                "name='" + userIdentifire.getName() + '\'' +
                ", id='" + userIdentifire.getId() + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", isLoggedIn='" + isLoggedIn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if( o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userIdentifire, user.userIdentifire);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userIdentifire);
    }
}
