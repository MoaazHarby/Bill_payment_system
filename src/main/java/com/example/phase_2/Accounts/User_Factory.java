package com.example.phase_2.Accounts;

import java.util.*;

import org.springframework.stereotype.*;

@Component
public class User_Factory {

    private  Vector<User> user_accounts;

    private static User_Factory obj=null;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private User_Factory() {user_accounts=new Vector<User>(0);}

    public static User_Factory getobj()
    {
        if (obj==null)
            obj = new User_Factory();

        return obj;


    }

   
    public String login(String mail, String pass) {

        for (int i = 0; i < user_accounts.size(); i++) {
            if (user_accounts.elementAt(i).email.equals(mail))
            {
                if (user_accounts.elementAt(i).password.equals(pass))
                {
                    return("login successful");
                    //return user_accounts.elementAt(i);
                }

                else {
                    return("Email found but, password is incorrect");
                    //return null;
                }
            }
        }
        return("Email not found, Consider_signing up");
        //return null;
    }

   
    public String signUp(String mail, String pass) {

        for (int i = 0; i < user_accounts.size(); i++) {
            if (user_accounts.elementAt(i).email.equals(mail))
            {
                return("Email is already used.");
                //return null;
            }
        }

        user_accounts.add(new User(mail,pass));
        return("User account created successfully");
        //return user_accounts.lastElement();
    }

    
    public Account signUp() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter user's email: ");
        String mail=sc.nextLine();
        System.out.println();

        System.out.print("Enter user's pass: ");
        String pass=sc.nextLine();
        System.out.println();

        for (int i = 0; i < user_accounts.size(); i++) {
            if (user_accounts.elementAt(i).email.equals(mail))
            {
                System.out.println("Email is already used.");
                return null;
            }
        }

        user_accounts.add(new User(mail,pass));
        System.out.println("User account created successfully");
        return user_accounts.lastElement();

    }

    
    public Account login() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter user's email: ");
        String mail=sc.nextLine();
        System.out.println();

        System.out.print("Enter user's pass: ");
        String pass=sc.nextLine();
        System.out.println();

        for (int i = 0; i < user_accounts.size(); i++) {
            if (user_accounts.elementAt(i).email.equals(mail))
            {
                if (user_accounts.elementAt(i).password.equals(pass))
                {
                    System.out.println("login successful");
                    return user_accounts.elementAt(i);
                }

                else {
                    System.out.println("Email found but, password is incorrect");
                    return null;
                }
            }
        }
        System.out.println("Email not found, Consider_signing up");
        return null;
    }

    public User get_user_by_id(int sent_id) {
        for (int i = 0; i < user_accounts.size(); i++) {
            if (user_accounts.elementAt(i).get_id()==sent_id)
            {
                return (user_accounts.elementAt(i));
            }
        }
        return null;
    }
}

