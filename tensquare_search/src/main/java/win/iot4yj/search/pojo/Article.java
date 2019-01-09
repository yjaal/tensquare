package win.iot4yj.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Description:
 * date: 2018/11/29 9:32
 * author: loveLy
 */

/**
 * @Document : 这个是ES的注解，在类使用，指定实体类的索引和类型。默认所有的属性都是索引的
 * 1、indexName ：　指定索引
 * 2、type：指定类型
 * 3、shards：指定分片的数量
 * 4、replicas：指定副本的数量
 */
@Document(indexName = "tensquare", type = "article")
public class Article implements Serializable {

	@Id
	private String id;//ID

	/**
	 * @Document :标注在属性上，用来指定属性的类型。其中的属性如下：
	 * analyzer：指定分词器，es中默认使用的标准分词器，比如我们需要指定中文IK分词器，可以指定值为ik_max_word
	 * type： 指定该属性在es中的类型，其中的值是FileType类型的值，比如FileType.Text类型对应es中的text类型
	 * index：指定该词是否需要索引，默认为true
	 * store：指定该属性内容是否需要存储，默认为
	 * fielddata ：指定该属性能否进行排序，因为es中的text类型是不能进行排序（已经分词了）
	 * searchAnalyzer ： 指定搜索使用的分词器
	 */
//	@Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	@Field(type = FieldType.text)
	private String title;//标题

//	@Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	@Field(type = FieldType.text)
	private String content;//文章正文

	private String state;//审核状态


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
