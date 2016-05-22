package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cidade;
import br.com.wideSolutions.zooe.entity.Tipo;
import br.com.wideSolutions.zooe.entity.TipoDeJogo;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Valor.class)
public class Valor_ { 

    public static volatile SingularAttribute<Valor, Cidade> cidade;
    public static volatile SingularAttribute<Valor, Tipo> tipo;
    public static volatile SingularAttribute<Valor, TipoDeJogo> tipodejogo;
    public static volatile SingularAttribute<Valor, BigDecimal> valor;
    public static volatile SingularAttribute<Valor, Integer> id;

}