/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;


public class Patient {
    public String name;
    public int id;
    public long phone;
    public String gender;
    public int age;
    public String date;
    public String disease;
    
    public static int count = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Patient.count = count;
    }
    
    
    public Patient(int id, String name, long phone, String gender, int age, String date, String disease){
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
        this.phone = phone;
        this.gender = gender;
        this.disease = disease;
    }
    
    public Patient(){
        
    }
    
    public String getInfo(){
        String s = "Name: " +name + "\nID: "+id+ "\nGender: "+gender+"\nAge: " + age+ "\nPhone Number: " +phone+ "\nDisease: " +disease+"\nRegistration Date: "+date;
        return s;
    }
    
    public Patient getPatient(Patient [] arr, int id){
        int n = arr.length; 
        
        for(int i = 0; i<n; i++){
            if(id == arr[i].id){
                return arr[i];
            }
        }
        return null;
    }
}
