/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;


public class Doctor {

    String name;
    int id;
    String specialization;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSpecialization() {
        return specialization;
    }
    

    public Doctor(String name, int id, String specialization) {
        this.name = name;
        this.id = id;
        this.specialization = specialization;
    }

    public Doctor() {
    }

    public void setInfo(String name, int id, String specialization) {
        this.name = name;
        this.id = id;
        this.specialization = specialization;
    }

    public Doctor getDoctor(Doctor[] arr, String spec) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (spec.equalsIgnoreCase(arr[i].specialization)) {
                return arr[i];
            }
        }
        return null;
    }

    public String getInfo() {
        String s = "Name: " + name + "\nID: " + id + "\nSpecialization: " + specialization;
        return s;
    }

    public Doctor getDoctorDisease(Doctor[] arr, String prob) {
        int n = arr.length;

        if (prob.equalsIgnoreCase("cold") || prob.equalsIgnoreCase("fever")) {
            Doctor d = getDoctor(arr, "Medicine");
            return d;
        }

        return null;
    }
}
