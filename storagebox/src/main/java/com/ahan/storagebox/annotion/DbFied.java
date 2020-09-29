package com.ahan.storagebox.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Time:2020/8/4
 * Author:ahan
 * Description:
 */

//@Target：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）注解（annotation）
// 可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）
// 、方法参数和本地变量（如循环变量、catch参数）。在注解类型的声明中使用了target可更加明晰其修饰的目标。
@Target(ElementType.FIELD) //作用域
@Retention(RetentionPolicy.RUNTIME) //注解运行时期
public @interface DbFied {
    String value();
}
