package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.PessoaCambista;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:59")
@StaticMetamodel(AluguelAjuda.class)
public class AluguelAjuda_ { 

    public static volatile SingularAttribute<AluguelAjuda, Boolean> automatico;
    public static volatile SingularAttribute<AluguelAjuda, Date> dtliberacao;
    public static volatile SingularAttribute<AluguelAjuda, BigDecimal> valor;
    public static volatile SingularAttribute<AluguelAjuda, Integer> id;
    public static volatile SingularAttribute<AluguelAjuda, PessoaCambista> cambista;
    public static volatile SingularAttribute<AluguelAjuda, String> descricao;

}