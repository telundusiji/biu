package site.teamo.magic.biu.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 爱做梦的锤子
 * @create 2020/10/14
 */
public class Test1<LINGLING> {
    private static String a = "a1";

    static {
        b = "b2";
    }

    private static String b = "b1";

    static {
        a = "a2";
    }


    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>() {{
            this.add(new Student("张三", 10));
            this.add(new Student("张三", 20));
            this.add(new Student("张三", 21));
            this.add(new Student("张三", 22));
            this.add(new Student("张三", 25));
            this.add(new Student("张三", 50));
            this.add(new Student("张三", 1));
        }};

        for (int i = students.size() - 1; i >= 0; i--) {

        }

        for (Student student : students) {

        }




        Stream<Student> studentStream = students.stream()
                .filter(student -> student.getAge() > 20)
                .filter(student -> student.getAge() < 30)
                .map(student -> {
                    student.setName(student.getName() + "青年人");
                    return student;
                });

//        studentStream.count();
//        studentStream.collect(Collectors.toList());
//        studentStream.collect(Collectors.)

        String collect = Arrays.stream(new String[]{"1", "2", "1"}).collect(Collectors.joining(","));
        System.out.println(collect);
        List<String> strings = Arrays.asList(new String[]{"1", "2", "1"});
        ArrayList<String> strings1 = new ArrayList<>(strings);

//        Stream.of().map()

        test(Food.ROU.ZHU);
        test(Food.ROU.ZHU);


    }


    public static void test(Food food){

    }

    public static enum Lin1{
        NAME1("男");

        public final String sex;

        public void test(){

        }



        Lin1(String sex) {
            this.sex = sex;
        }
    }

}
