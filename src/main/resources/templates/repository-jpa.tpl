package ${PACKAGE};

import ${BASE_PACKAGE}.entity.${ENTITY_NAME};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${CLASS_NAME}
        extends JpaRepository<${ENTITY_NAME}, Long> {

}