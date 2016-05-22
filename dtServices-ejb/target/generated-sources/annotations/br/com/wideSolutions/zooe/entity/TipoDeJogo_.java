package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.JogoDetalhado;
import br.com.wideSolutions.zooe.entity.TipoDeJogoLoteria;
import br.com.wideSolutions.zooe.entity.Valor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(TipoDeJogo.class)
public class TipoDeJogo_ { 

    public static volatile SingularAttribute<TipoDeJogo, String> abreviacao;
    public static volatile SetAttribute<TipoDeJogo, TipoDeJogoLoteria> tipoDeJogoLoterias;
    public static volatile SetAttribute<TipoDeJogo, Valor> valors;
    public static volatile SingularAttribute<TipoDeJogo, Integer> id;
    public static volatile SingularAttribute<TipoDeJogo, String> descricao;
    public static volatile SetAttribute<TipoDeJogo, JogoDetalhado> jogoDetalhados;

}