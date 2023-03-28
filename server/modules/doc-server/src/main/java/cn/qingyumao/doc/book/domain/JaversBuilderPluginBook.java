package cn.qingyumao.doc.book.domain;

import org.javers.core.JaversBuilder;
import org.javers.core.JaversBuilderPlugin;
import org.javers.core.metamodel.clazz.EntityDefinition;
import org.springframework.stereotype.Component;

@Component
public class JaversBuilderPluginBook implements JaversBuilderPlugin {

    @Override
    public void beforeAssemble(JaversBuilder javersBuilder) {
        javersBuilder.registerType(new EntityDefinition(Book.class, "id"));
    }

}
