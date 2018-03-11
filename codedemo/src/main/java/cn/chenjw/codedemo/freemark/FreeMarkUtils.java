package  cn.chenjw.codedemo.freemark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import cn.chenjw.codedemo.bean.User;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkUtils {
	public static void main(String[] args) {
		User u = new User();u.setAge(12);u.setId("11");u.setName("abv");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("user", u);
		analysisTemp("user.ftl", "UTF-8", root);
	}
	
	public static void analysisTemp(String templateName,
			String templateEncoding, Map<?, ?> root){
		try {
			Configuration config = new Configuration();
			//File file = new File("D:/A");
			//指定模板路径并加载
			File file = new File("src/main/resources");
			config.setDirectoryForTemplateLoading(file);
			//设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			
			Template template = config.getTemplate(templateName,templateEncoding);
			Writer out = new OutputStreamWriter(new FileOutputStream("D:/A/0813-1.txt"));
			
			template.process(root, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
