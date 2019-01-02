package util;

import entity.QueryCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 在使用条件进行查询的时候需要写很多Predicate，这里通过注解加反射简化这个过程，暂时还不是很完善
 *
 * @author yj
 */
public class Obj2PredicateList {

	public static <T> Specification getQueryPredicates(T obj) {
		//这是lambda写法
		return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> list = new ArrayList<>();
			//获取类的所有属性字段
			Field[] fields = obj.getClass().getDeclaredFields();
			Arrays.asList(fields).forEach(field -> {
				//设置可访问
				field.setAccessible(true);
				//获取字段上的QueryCondition注解
				Annotation annotation = field.getAnnotation(QueryCondition.class);
				//如果该注解存在且该字段值不为空
				try {
					if (!Objects.equals(null, annotation) && !Objects.equals(null, field.get(obj))) {
						switch (((QueryCondition) annotation).condition()) {
							case LIKE:
								list.add(criteriaBuilder.like((Expression<String>) root.get(field.getName()).as(field.getType()), "%" + field.get(obj) + "%"));
								break;
							case EQUAL:
								list.add(criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), field.get(obj)));
								break;
							default:
								break;
						}
					}
				} catch (Exception e) {
					//不能这样处理
					//TODO
					e.printStackTrace();
				}
			});
			return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
		};
	}
}
