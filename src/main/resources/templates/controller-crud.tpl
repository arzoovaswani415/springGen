package ${PACKAGE};

import ${BASE_PACKAGE}.service.${ENTITY_NAME}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${API_PATH}")
public class ${CLASS_NAME} {

    @Autowired
    private ${ENTITY_NAME}Service service;

}