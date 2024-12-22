package com.example.Human;

// Human class
public class Human {
    private final String gender;
    private final int age;
    private final String lastName;

    private Human(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Человек{" +
                "Пол='" + gender + '\'' +
                ", Возраст=" + age +
                ", Фамилия='" + lastName + '\'' +
                '}';
    }

    public static class Builder {
        private String gender;
        private int age;
        private String lastName;

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}
