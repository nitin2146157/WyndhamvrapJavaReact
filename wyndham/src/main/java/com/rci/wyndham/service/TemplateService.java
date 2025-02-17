package com.rci.wyndham.service;

import freemarker.template.Template;

import java.util.Map;

/**
 * Defines the functionality available for loading templates.
 * @author fernando.alves.
 */
public interface TemplateService {

    /**
     * Gets the template with the given name.
     * The name should contain the path to the template considering the template loader path.
     * To check what the template loader path is, please check "applicationContext.xml" file at bean "freemarkerConfig".
     * @param templateName the template name
     * @retur the template
     */
    Template getTemplate(String templateName);

    /**
     * Gets the template with the given name, replace the placeholders with the given map, and return the template
     * output after replacing the placeholders..
     * The name should contain the path to the template considering the template loader path.
     * To check what the template loader path is, please check "applicationContext.xml" file at bean "freemarkerConfig".
     * @param templateName the template name
     * @param values the map containing all placeholders and its values
     * @return the output string after replacing the placeholders
     */
    String getTemplateOutput(String templateName, Map values);

}
