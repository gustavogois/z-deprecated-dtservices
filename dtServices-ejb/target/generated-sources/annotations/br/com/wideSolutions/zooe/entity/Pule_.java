package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.JogoDetalhado;
import br.com.wideSolutions.zooe.entity.Mobile;
import br.com.wideSolutions.zooe.entity.PessoaCambista;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Pule.class)
public class Pule_ { 

    public static volatile SingularAttribute<Pule, Date> data;
    public static volatile SingularAttribute<Pule, Mobile> mobile;
    public static volatile SingularAttribute<Pule, String> json;
    public static volatile SingularAttribute<Pule, Integer> jsonVersion;
    public static volatile SingularAttribute<Pule, Integer> id;
    public static volatile SingularAttribute<Pule, PessoaCambista> cambista;
    public static volatile SetAttribute<Pule, JogoDetalhado> jogoDetalhados;

}