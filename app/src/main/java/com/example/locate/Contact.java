package com.example.locate;

public class Contact {

    String _name;
    String _phone_number;
    public Contact(){   }
    public Contact(String name, String _phone_number){

        this._name = name;
        this._phone_number = _phone_number;
    }




    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
