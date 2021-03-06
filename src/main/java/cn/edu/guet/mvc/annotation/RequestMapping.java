package cn.edu.guet.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//此注解作用在《方法》上面
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping{
    String value() default "";
}