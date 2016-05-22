package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.AluguelAjuda;
import br.com.wideSolutions.zooe.entity.Mobile;
import br.com.wideSolutions.zooe.entity.PeriodicidadeComissao;
import br.com.wideSolutions.zooe.entity.PessoaRecolhe;
import br.com.wideSolutions.zooe.entity.Pule;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(PessoaCambista.class)
public class PessoaCambista_ extends Pessoa_ {

    public static volatile SingularAttribute<PessoaCambista, Boolean> teste;
    public static volatile SingularAttribute<PessoaCambista, String> observacao;
    public static volatile SingularAttribute<PessoaCambista, BigDecimal> comissao;
    public static volatile SetAttribute<PessoaCambista, AluguelAjuda> aluguelAjudas;
    public static volatile SetAttribute<PessoaCambista, Mobile> mobiles;
    public static volatile SingularAttribute<PessoaCambista, PeriodicidadeComissao> periodicidadeComissao;
    public static volatile SingularAttribute<PessoaCambista, Boolean> bloqueado;
    public static volatile SingularAttribute<PessoaCambista, BigDecimal> recolheComissao;
    public static volatile SingularAttribute<PessoaCambista, BigDecimal> saldo;
    public static volatile SingularAttribute<PessoaCambista, PessoaRecolhe> recolhe;
    public static volatile SetAttribute<PessoaCambista, Pule> jogos;

}