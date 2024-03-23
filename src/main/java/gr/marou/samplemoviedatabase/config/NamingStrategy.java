package gr.marou.samplemoviedatabase.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Custom implementation of Hibernate's PhysicalNamingStrategyStandardImpl
 * to convert all column names to uppercase.
 */
public class NamingStrategy extends PhysicalNamingStrategyStandardImpl {

    /**
     * Converts the given column name to uppercase.
     *
     * @param name    the original column name
     * @param context the JDBC environment context
     * @return the column name converted to uppercase
     */
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String columnName = name.getText();
        String uppercaseColumnName = columnName.toUpperCase();
        return Identifier.toIdentifier(uppercaseColumnName);
    }
}
