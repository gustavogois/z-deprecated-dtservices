package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Loteria;
import br.com.wideSolutions.zooe.entity.Resultado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Sorteio.class)
public class Sorteio_ { 

    public static volatile ListAttribute<Sorteio, Resultado> resultados;
    public static volatile SingularAttribute<Sorteio, Integer> id;
    public static volatile SingularAttribute<Sorteio, Loteria> loteria;
    public static volatile SingularAttribute<Sorteio, Date> dtApuracao;

}