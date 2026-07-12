package ${PACKAGE};

import ${BASE_PACKAGE}.repository.${ENTITY_NAME}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${CLASS_NAME} {

    @Autowired
    private ${ENTITY_NAME}Repository repository;

}