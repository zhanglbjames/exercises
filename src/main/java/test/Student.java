package test;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/6.
 */
public class Student extends Person {
    private String school;
    private String major;

    public Student(String name, int age, String school, String major) {
        super(name, age);
        this.school = school;
        this.major = major;
    }
    // 重写父类方法，方法内调用父类方法
    public void speak() {
        super.speak();
        System.out.println("My come from  " + school + " and my major is " + major);
    }
    public static void main(String[] args) {
        Student student = new Student("lbjames",24,"MCU","CS");
        student.speak();
        Person person = student;
        person.speak();
    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void speak() {
        System.out.println("My name is " + name + " and age is " + age);
    }
}
