package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cidade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:59")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, String> senha;
    public static volatile SingularAttribute<Pessoa, Cidade> cidade;
    public static volatile SingularAttribute<Pessoa, Date> dtCadastro;
    public static volatile SingularAttribute<Pessoa, String> nome;
    public static volatile SingularAttribute<Pessoa, Integer> id;

}