package com.bigknow.ftl;

import com.bigknow.demo.model.Book;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by Zhan on 2016/11/14 0014.
 */
public class FTLDemo {
    public static void main(String[] args) throws Exception{
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setDirectoryForTemplateLoading(new File("/WEB-INF/templets/demo"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        Template template = cfg.getTemplate("demo.ftl");
        Book book = new Book();
        book.setBookId("A0002");
        book.setBookName("king of rings");
        book.setAuthor("M");
        Writer out = new OutputStreamWriter(System.out);
        template.process(book,out);
        out.flush();
    }
}
