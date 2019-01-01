package entity;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//在编译期间有效
@Target(ElementType.FIELD)//只能在属性上使用
@Inherited
public @interface QueryCondition {

	String field();//字段名字
	QueryType condition();//查询方式，如 = 、like
}
