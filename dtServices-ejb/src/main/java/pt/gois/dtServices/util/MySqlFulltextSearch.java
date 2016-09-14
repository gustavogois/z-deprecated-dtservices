package pt.gois.dtServices.util;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

public class MySqlFulltextSearch extends StandardSQLFunction implements SQLFunction{

    public MySqlFulltextSearch(){
	super("MySqlFulltextSearch");
    }
    @Override
    public Type getReturnType(Type arg0, Mapping arg1) throws QueryException {
	return new BooleanType();
    }

    @Override
    public boolean hasArguments() {
	return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
	return false;
    }

    @Override
    public String render(Type arg0, @SuppressWarnings("rawtypes") List args, SessionFactoryImplementor arg2) throws QueryException {
	if (args.size() != 2) {
	    throw new IllegalArgumentException("The function must be passed 2 arguments");
	}

	String field = (String) args.get(0);
	String value = (String) args.get(1);
	String fragment = " MATCH(" + field + ") AGAINST('" + value + "') ";
	return fragment;
    }

}
