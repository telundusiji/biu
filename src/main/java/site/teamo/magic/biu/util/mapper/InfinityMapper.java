package site.teamo.magic.biu.util.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface InfinityMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
