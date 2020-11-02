package site.teamo.biu.download.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 爱做梦的锤子
 * @create 2020/9/18
 */
@Data
@AllArgsConstructor
@Builder
public class Command {
    /**
     * 命令名称
     */
    public final String name;
    /**
     * 命令内容
     */
    public final String command;
    /**
     * 命令描述
     */
    public final String description;
}
