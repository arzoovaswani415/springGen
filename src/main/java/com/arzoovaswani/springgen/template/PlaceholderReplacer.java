package com.arzoovaswani.springgen.template;

import java.util.Map;

public class PlaceholderReplacer {

    /**
     * Replaces every placeholder found in the template.
     *
     * Example:
     *
     * ${CLASS_NAME}
     *      ↓
     * User
     */
    public String replace(String template,
                          Map<String, String> placeholders) {

        String result = template;

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {

            result = result.replace(
                    "${" + entry.getKey() + "}",
                    entry.getValue()
            );

        }

        return result;

    }

}