package cn.chenjw.codedemo.file.xml.xstream;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import com.google.common.base.CharMatcher;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class PersonTest {

	public static void main(String[] args) {
		Person bean=new Person();
		bean.setId("1");
		bean.setAge(6);
		bean.setName("aa");
		//XStream xstream = new XStream();
		
		/*方法一：方法有过时的
		XStream进行XML与对象的互相转换 下划线问题
		*/
		//XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyReplacer("-_", "_")));
		
		//解决下划线问题和给某个字段加CDATA标记
		boolean isAddCDATA = false;
		XStream xstream = new XStream(new XppDriver(new NoNameCoder()){
			public HierarchicalStreamWriter createWriter(Writer out){
				
				return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("_-", "_")){
					String name = "";

                    @SuppressWarnings("rawtypes")
                    @Override
                    public void startNode(final String name, final Class clazz) {
                        if ((name != null) && (name.equals("age"))) {
                            this.name = name;
                        } else {
                            this.name = "";
                        }
                        super.startNode(name, clazz);
                    }
                    
                    @Override
                    protected void writeText(final QuickWriter writer, final String text) {
                        if (isAddCDATA && (this.name.equals("age"))) {
                            writer.write("<![CDATA[");
                            writer.write(CharMatcher.anyOf("\r\n\t ").trimFrom(text));
                            writer.write("]]>");
                        } else {
                            super.writeText(writer, text);
                        }
                    }
				};
				
			} 
		});
		
		
		// 设定根标签
        xstream.alias("Q_person", Person.class);
		//如果没有这句，xml中的根元素会是<包.类名>
		xstream.processAnnotations(bean.getClass());//通过注解方式的，一定要有这句话
		
		String xml = xstream.toXML(bean);
		System.out.println(xml);
		
		bean=(Person)xstream.fromXML(xml);
		System.out.println(bean);
		
	}

}
