package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.JogoDetalhado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:59")
@StaticMetamodel(Colocacao.class)
public class Colocacao_ { 

    public static volatile SingularAttribute<Colocacao, Integer> id;
    public static volatile SingularAttribute<Colocacao, String> descricao;
    public static volatile SetAttribute<Colocacao, JogoDetalhado> jogos;

}