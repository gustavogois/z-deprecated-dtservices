package br.com.wideSolutions.zooe.entity;

import br.com.wideSolutions.zooe.entity.Cotadas;
import br.com.wideSolutions.zooe.entity.Loteria;
import br.com.wideSolutions.zooe.entity.Pessoa;
import br.com.wideSolutions.zooe.entity.Regiao;
import br.com.wideSolutions.zooe.entity.Valor;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-21T22:23:58")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile SingularAttribute<Cidade, BigDecimal> vlrLiberarMilharBrinde;
    public static volatile SingularAttribute<Cidade, Integer> minparaCancelamentos;
    public static volatile SingularAttribute<Cidade, Boolean> imprimeFolhaCompleta;
    public static volatile SingularAttribute<Cidade, Integer> qtdResultados;
    public static volatile SingularAttribute<Cidade, String> nome;
    public static volatile SetAttribute<Cidade, Valor> valors;
    public static volatile SetAttribute<Cidade, Cotadas> cotadases;
    public static volatile SingularAttribute<Cidade, Regiao> regiao;
    public static volatile SingularAttribute<Cidade, BigDecimal> vlrMaximoCentena;
    public static volatile SingularAttribute<Cidade, String> cabecalho;
    public static volatile SetAttribute<Cidade, Pessoa> pessoas;
    public static volatile SingularAttribute<Cidade, Integer> numMaximoVias;
    public static volatile SingularAttribute<Cidade, Boolean> duplaXataPagaDobrado;
    public static volatile SetAttribute<Cidade, Loteria> loterias;
    public static volatile SingularAttribute<Cidade, BigDecimal> vlrMaximoMilhar;
    public static volatile SingularAttribute<Cidade, Integer> id;
    public static volatile SingularAttribute<Cidade, String> aviso;

}