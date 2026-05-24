package hashset.UserActions;

import java.time.LocalDateTime;
import java.util.HashSet;

import hashset.Users.User;

public class UserActions {
    HashSet<User> userList = new HashSet<>();

    public void registerUser(String name, String password){
        User newUser = new User(name, password);
        if(!userList.contains(newUser)){
            userList.add(newUser);
            System.out.println("User registered" + newUser.getName());
        } else {
            System.out.println("User already exists!");
        }
    }

    public User loginUser(String name, String password){
        for(var user : userList){
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                user.setLoggedIn(true);
                user.setLastLoginDate(LocalDateTime.now());
                return user;
            }
        }
        return null;
    }

    public void logoutUser(Integer userId){
        for(var user : userList){
            if(user.getId().equals(userId)){
                user.setLoggedIn(false);
                System.out.println("Logout from accoubnt.");
                return;
            }
        }
    }

    public boolean isUserRegistered(String login){
        for(var user : userList){
            if(user.getName().equals(login)){
                System.out.println("User registered.");
                return true;
            }
        }
        
        return false;
    }

    public void removeUser(Integer id){
        for(var user : userList){
            if(user.getId().equals(id)){
                userList.remove(user);
                System.out.println("User was removed.");
            }
        }
    }

    public void printTotalUniqueUser(){
        for(var user : userList){
            System.out.println("All uniques users" + user);
        }
    }

    public void displayAllUsers(){
        for(var user : userList){
            System.out.println("Student{" + "name='" + user.getName() + "id='" + user.getName()  + "password='" + user.getPassword() +  "lastLoginDate='" + user.getLastLoginDate() + "isLoggedIn='" + user.isLoggedIn() + 
                '}');
        }
    }

    
}
