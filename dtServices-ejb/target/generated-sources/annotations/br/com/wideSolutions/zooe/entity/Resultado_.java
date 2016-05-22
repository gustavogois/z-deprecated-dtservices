package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Colocacao;
import br.com.wideSolutions.zooe.entity.Sorteio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Resultado.class)
public class Resultado_ { 

    public static volatile SingularAttribute<Resultado, Colocacao> colocacao;
    public static volatile SingularAttribute<Resultado, Integer> premio;
    public static volatile SingularAttribute<Resultado, Integer> id;
    public static volatile SingularAttribute<Resultado, Sorteio> sorteio;

}