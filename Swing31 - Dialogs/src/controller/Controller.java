package controller;

import gui.FormEvent;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {
    DataBase db = new DataBase();

    public List<Person> getPeople(){
        return db.getPeople();
    }

    public void removePerson(int index) {
        db.removePerson(index);
    }
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
        } else if (empCat.equals("self-employed")) {
            employmentCategory = EmploymentCategory.selfEmployed;
        } else if (empCat.equals("unemployed")) {
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

    public void saveToFile(File file) throws IOException {
        db.saveToFile(file);
    }

    public void loadFromFile(File file) throws IOException{
        db.loadFromFile(file);
    }
}
