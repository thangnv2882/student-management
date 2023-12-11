package com.pmmnm.StudentManagement.application.input.student;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class CreateStudentInput {

    String id;

    String name;

    String phoneNumber;

    public CreateStudentInput() {
    }

    public CreateStudentInput(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
