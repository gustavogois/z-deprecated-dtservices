package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Colocacao;
import br.com.wideSolutions.zooe.entity.Loteria;
import br.com.wideSolutions.zooe.entity.Pule;
import br.com.wideSolutions.zooe.entity.TipoDeJogo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(JogoDetalhado.class)
public class JogoDetalhado_ { 

    public static volatile SingularAttribute<JogoDetalhado, Colocacao> colocacao;
    public static volatile SingularAttribute<JogoDetalhado, Integer> palpite;
    public static volatile SingularAttribute<JogoDetalhado, Date> data;
    public static volatile SingularAttribute<JogoDetalhado, BigDecimal> valor;
    public static volatile SingularAttribute<JogoDetalhado, Pule> pule;
    public static volatile SingularAttribute<JogoDetalhado, Integer> id;
    public static volatile SingularAttribute<JogoDetalhado, Loteria> loteria;
    public static volatile SingularAttribute<JogoDetalhado, Pule> jogo;
    public static volatile SingularAttribute<JogoDetalhado, TipoDeJogo> tipoDeJogo;

}