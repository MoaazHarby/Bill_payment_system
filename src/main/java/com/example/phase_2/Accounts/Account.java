package com.example.phase_2.Accounts;


public abstract class Account {
    protected String email;
    public int id;
    protected String password;
    public int show_options(){return 0;};

    public String get_mail() {
        return email;
    }

    public String get_password() {
        return password ;
    }
    public int get_id() {
        return id ;
    }


}

