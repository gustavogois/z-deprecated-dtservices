package pt.gois.dtServices.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;

public class MyMySQL5InnoDBDialect extends MySQL5InnoDBDialect {

    public MyMySQL5InnoDBDialect() {
	super();
	registerFunction("searchAddress", new StandardSQLFunction("searchAddress"));
	registerFunction("fulltextSearch", new VarArgsSQLFunction( "MATCH(", ") AGAINST('", "')" ) ) ;
    }

}
