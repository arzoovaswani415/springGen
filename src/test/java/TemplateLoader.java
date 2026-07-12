
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TemplateLoader {

    /**
     * Loads a template from src/main/resources/templates.
     *
     * Example:
     * load("entity.tpl")
     */
    public String load(String templateName) throws IOException {

        String resourcePath = "/templates/" + templateName;

        try (InputStream inputStream =
                     getClass().getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new IOException(
                        "Template not found: " + templateName
                );
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        }

    }


}
