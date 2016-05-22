package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.PessoaCambista;
import br.com.wideSolutions.zooe.entity.Pule;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Mobile.class)
public class Mobile_ { 

    public static volatile SingularAttribute<Mobile, String> id;
    public static volatile SingularAttribute<Mobile, PessoaCambista> cambista;
    public static volatile SingularAttribute<Mobile, String> modelo;
    public static volatile SetAttribute<Mobile, Pule> jogos;

}