package site.teamo.magic.biu.test;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 爱做梦的锤子
 * @create 2020/10/12
 */
@Data
@Builder
@ToString
public class Student {
    private String name;
    private Integer age;
}
