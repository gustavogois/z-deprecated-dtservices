package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cidade;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Cotadas.class)
public class Cotadas_ { 

    public static volatile SingularAttribute<Cotadas, Cidade> cidade;
    public static volatile SingularAttribute<Cotadas, Integer> milhar;
    public static volatile SingularAttribute<Cotadas, Date> dtInclusao;
    public static volatile SingularAttribute<Cotadas, BigDecimal> percentual;
    public static volatile SingularAttribute<Cotadas, Integer> id;
    public static volatile SingularAttribute<Cotadas, Date> updateDt;
    public static volatile SingularAttribute<Cotadas, Date> dtTermino;

}