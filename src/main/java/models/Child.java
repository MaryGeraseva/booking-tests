package models;


import java.util.Objects;

public class Child {
    int age;

    public Child(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return age == child.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}