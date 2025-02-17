package com.rci.wyndham.serviceimpl;

import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.service.TemplateService;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static com.rci.wyndham.model.BaseObject.LOGGER;

@Service
public class TemplateServiceImpl extends BaseObject implements TemplateService {

    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    public Template getTemplate(String templateName) {
        try {
            return freemarkerConfig.getConfiguration().getTemplate(templateName);
        } catch (IOException e) {
            LOGGER.error("error while loading template " + templateName, e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateOutput(String templateName, Map values) {
        try {
            Template template = getTemplate(templateName);
            StringWriter out = new StringWriter();
            template.process(values, out);
            return out.toString();
        } catch (Exception e) {
            LOGGER.error("error getting template output for " + templateName, e);
            return null;
        }
    }

}
