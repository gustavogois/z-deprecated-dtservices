package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cidade;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:59")
@StaticMetamodel(Regiao.class)
public class Regiao_ { 

    public static volatile SingularAttribute<Regiao, String> nome;
    public static volatile SetAttribute<Regiao, Cidade> cidades;
    public static volatile SingularAttribute<Regiao, Integer> id;

}