package site.teamo.magic.biu.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 爱做梦的锤子
 * @create 2020/10/12
 */
public class OptionalBean<T> {
    private static final OptionalBean<?> EMPTY = new OptionalBean();
    private final T value;

    private OptionalBean() {
        this.value = null;
    }

    /**
     * 使用null值构造抛出异常
     *
     * @param value
     */
    private OptionalBean(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * 包装一个可能为空的bean
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> OptionalBean<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * 包装一个不能为空的bean
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> OptionalBean<T> of(T value) {
        return new OptionalBean<T>(value);
    }

    /**
     * 取出具体的值
     *
     * @return
     */
    public T get() {
        return Objects.isNull(value) ? null : value;
    }

    /**
     * 获取一个空值常量
     *
     * @param <T>
     * @return
     */
    public static <T> OptionalBean<T> empty() {
        OptionalBean<T> none = (OptionalBean<T>) EMPTY;
        return none;
    }


    /**
     * 如果目标值，则返回一个默认值
     *
     * @param other
     * @return
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * 如果目标值为空则，则使用表达式获取一个值
     *
     * @param other
     * @return
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * 如果目标值为空，则抛出一个异常
     *
     * @param exceptionSupplier
     * @param <E>
     * @return
     * @throws E
     */
    public <E extends Throwable> T orElseThrow(Supplier<? extends E> exceptionSupplier) throws E {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPreset(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    /**
     * 对象不为空时使用表达式对其进行转换
     *
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> OptionalBean<R> map(Function<? super T, ? extends R> mapper) {
        return Objects.isNull(value) ? OptionalBean.empty() : OptionalBean.ofNullable(mapper.apply(value));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

