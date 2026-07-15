package controller;

import gui.FormEvent;
import gui.TextPanel;
import model.*;

import javax.xml.crypto.Data;

public class Controller {
    DataBase db = new DataBase();

    public void addPerson(FormEvent ev){
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCat = ev.getAgeCategory();
        String empCat = ev.getEmploymentCategory();
        boolean isUs = ev.isUsCitizen();
        String taxId = ev.getTaxId();
        String gender = ev.getGender();

        AgeCategory ageCategory = null;

        switch (ageCat) {
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;
        }

        EmploymentCategory employmentCategory;

        if (empCat.equals("employed")) {
            employmentCategory = EmploymentCategory.employed;
        } else if (empCat.equals("employed")) {
            employmentCategory = EmploymentCategory.selfEmployed;
        } else if (empCat.equals("employed")) {
            employmentCategory = EmploymentCategory.unemployed;
        } else{
            employmentCategory = EmploymentCategory.other;
            System.err.println(empCat);
        }

        Gender genderCat;

        if (gender.equals("male")){
            genderCat = Gender.male;
        } else{
            genderCat = Gender.female;
        }

        Person person = new Person(name, occupation, ageCategory, employmentCategory, taxId, isUs, genderCat);

        db.addPerson(person);
    }
}
