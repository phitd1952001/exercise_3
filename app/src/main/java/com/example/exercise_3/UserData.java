package com.example.exercise_3;

public class UserData {
    private String name;
    private String email;
    private String dateOfBirth;
    private String selectedImageTag;

    public UserData(String name, String email, String dateOfBirth, String selectedImageTag) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.selectedImageTag = selectedImageTag;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSelectedImageTag() {
        return selectedImageTag;
    }
}
