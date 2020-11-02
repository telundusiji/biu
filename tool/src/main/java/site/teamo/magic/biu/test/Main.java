package site.teamo.magic.biu.test;

import org.junit.Test;
import scala.Int;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 爱做梦的锤子
 * @create 2020/10/12
 */
public class Main {

    @Test
    public void test1() {



        List<Student> students = Arrays.asList(new Student[]{
                Student.builder().name("张三").age(10).build(),
                Student.builder().name("李四").age(15).build()
        });







        for (Student student : students) {

        }
        while (students.iterator().hasNext())

        for (Student student : students) {

        }

        students.forEach(System.out::println);
        System.out.println("****");
        Stream<Student> studentStream = students.stream().map(student -> {
            student.setAge(student.getAge() + 1);
            System.out.println(student);
            return student;
        });

        students.stream().filter(student -> student.getAge()<11).collect(Collectors.toList()).forEach(System.out::println);
        studentStream.findFirst().get();
        List<Student> collect1 = studentStream.collect(Collectors.toList());
//        List<Student> collect2 = studentStream.collect(Collectors.toList());
        System.out.println("****");
        students.forEach(System.out::println);
        System.out.println("****");

//        collect1.forEach(System.out::println);
//        collect2.forEach(System.out::println);

        Set<Node> map = new HashSet<>();

        List<Object> objects = Collections.synchronizedList(new ArrayList<>());



    }
}

class Node{
    private Node pre;
    private Node next;
    private Object object;
}