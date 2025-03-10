package a000_SpringBasics.dao;

import java.util.UUID;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

public class User {

    private UUID userId;
    private String name;
    private int age;
    private String gender;

    public User(UUID userId, String name, int age, String gender) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User(String name, int age, String gender) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static class Dto {
        private String name;
        private int age;
        private String gender;

        public Dto(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public User toUser() {
            return new User(name, age, gender);
        }
    }
}
