import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Created by Home on 09.08.2017.
 */
public class PageGenerator {
    private static PageGenerator ourInstance = new PageGenerator();
    private static final Configuration CFG = new Configuration(Configuration.VERSION_2_3_26);

    public static PageGenerator getInstance() {
        return ourInstance;
    }

    private PageGenerator() {
    }

    public String getPage(String fileName, Map<String, Object> root){
        Writer writer = new StringWriter();
        CFG.setDefaultEncoding("UTF-8");

        try {
            FileTemplateLoader templateLoader = new FileTemplateLoader(new File("tml"));
            CFG.setTemplateLoader(templateLoader);
            Template template = CFG.getTemplate(fileName);
            template.process(root, writer);
        }
        catch (IOException | TemplateException e){
            e.printStackTrace();
        }
        return writer.toString();
    }
}
