package entities.physicalNamingStrategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AcmeCorpPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final Map<String, String> ABBREVIATIONS;

    static {
        ABBREVIATIONS = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        ABBREVIATIONS.put("account", "acct");
        ABBREVIATIONS.put("number", "num");
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        List<String> parts = splitAndReplace(logicalName.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                String.join("_", parts),
                logicalName.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        List<String> parts = splitAndReplace(logicalName.getText());
        if (!"seq".equals(parts.get(parts.size() - 1))) {
            parts.add("seq");
        }
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                String.join("_", parts),
                logicalName.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        List<String> parts = splitAndReplace(logicalName.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                String.join("_", parts),
                logicalName.isQuoted()
        );
    }

    private List<String> splitAndReplace(String name) {
        return Arrays.stream(splitByCharacterTypeCamelCase(name))
                .filter(e -> e != null && e.trim().length() > 0)
                .map(p -> ABBREVIATIONS.getOrDefault(p, p).toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    private String[] splitByCharacterTypeCamelCase(String s) {
        return s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }
}
