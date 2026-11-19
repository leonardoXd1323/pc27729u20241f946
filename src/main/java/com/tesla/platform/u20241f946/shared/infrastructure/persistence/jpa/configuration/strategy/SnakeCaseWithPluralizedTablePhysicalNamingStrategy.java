package com.tesla.platform.u20241f946.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/** Converts database names to snake case and pluralizes table names. */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy
        extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment environment) {
        if (name == null) {
            return null;
        }
        return Identifier.toIdentifier(pluralize(toSnakeCase(name.getText())), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment environment) {
        return toSnakeCaseIdentifier(name);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment environment) {
        return toSnakeCaseIdentifier(name);
    }

    private static Identifier toSnakeCaseIdentifier(Identifier name) {
        if (name == null) {
            return null;
        }
        return Identifier.toIdentifier(toSnakeCase(name.getText()), name.isQuoted());
    }

    private static String toSnakeCase(String name) {
        return name.replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .toLowerCase(Locale.ROOT);
    }
}
