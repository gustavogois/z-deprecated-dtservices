package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cidade;
import br.com.wideSolutions.zooe.entity.JogoDetalhado;
import br.com.wideSolutions.zooe.entity.Sorteio;
import br.com.wideSolutions.zooe.entity.TipoDeJogoLoteria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:59")
@StaticMetamodel(Loteria.class)
public class Loteria_ { 

    public static volatile SingularAttribute<Loteria, Cidade> cidade;
    public static volatile SingularAttribute<Loteria, Boolean> quinta;
    public static volatile SingularAttribute<Loteria, Boolean> segunda;
    public static volatile SingularAttribute<Loteria, Date> hrEncerramento;
    public static volatile SingularAttribute<Loteria, Date> hrEncerramentoDescarga;
    public static volatile SingularAttribute<Loteria, String> abreviacao;
    public static volatile SingularAttribute<Loteria, Boolean> domingo;
    public static volatile SetAttribute<Loteria, TipoDeJogoLoteria> tipoDeJogoLoterias;
    public static volatile SingularAttribute<Loteria, String> nome;
    public static volatile SingularAttribute<Loteria, Boolean> terca;
    public static volatile SingularAttribute<Loteria, Boolean> quarta;
    public static volatile SetAttribute<Loteria, Sorteio> sorteios;
    public static volatile SingularAttribute<Loteria, Boolean> descarga;
    public static volatile SingularAttribute<Loteria, Boolean> sexta;
    public static volatile SingularAttribute<Loteria, Boolean> sabado;
    public static volatile SingularAttribute<Loteria, Integer> id;
    public static volatile SetAttribute<Loteria, JogoDetalhado> jogoDetalhados;

}