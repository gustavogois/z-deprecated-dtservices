package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Loteria;
import br.com.wideSolutions.zooe.entity.TipoDeJogo;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(TipoDeJogoLoteria.class)
public class TipoDeJogoLoteria_ { 

    public static volatile SingularAttribute<TipoDeJogoLoteria, BigDecimal> valor;
    public static volatile SingularAttribute<TipoDeJogoLoteria, Integer> id;
    public static volatile SingularAttribute<TipoDeJogoLoteria, Loteria> loteria;
    public static volatile SingularAttribute<TipoDeJogoLoteria, TipoDeJogo> tipoDeJogo;

}