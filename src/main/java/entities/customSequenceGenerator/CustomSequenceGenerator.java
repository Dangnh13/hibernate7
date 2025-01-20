package entities.customSequenceGenerator;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.Optimizer;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Member;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class CustomSequenceGenerator implements IdentifierGenerator {

    public CustomSequenceGenerator(
            Sequence config,
            Member annotatedMember,
            GeneratorCreationContext context) {
    }

    @Override
    public Long generate(
            SharedSessionContractImplementor session,
            Object object) {
        Long id = (long) (Math.random()*10);
        return id;
    }
}

// ----------------------------------------

@IdGeneratorType(CustomSequenceGenerator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
 @interface Sequence {
    String name();
    int startWith() default 1;
    int incrementBy() default 50;
    Class<? extends Optimizer> optimizer() default Optimizer.class;
}

