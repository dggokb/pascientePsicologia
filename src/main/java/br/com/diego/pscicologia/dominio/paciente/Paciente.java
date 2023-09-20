package br.com.diego.pscicologia.dominio.paciente;

import br.com.diego.pscicologia.comum.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;

public class Paciente extends Entidade {

    private String nome;
    private String endereco;
    private LocalDate dataDeInicio;
    private Quantidade quantidadeDeDiasNoMes;
    private Moeda valorPorSessao;
    private Boolean inativo;
    private Tipo tipo;
    @PersistenceCreator
    public Paciente(String nome,
                    String endereco,
                    Moeda valorPorSessao) {
        validarCamposObrigatorios(nome, endereco, valorPorSessao);
        this.nome = nome;
        this.endereco = endereco;
        this.dataDeInicio = LocalDate.now();
        this.valorPorSessao = valorPorSessao;
        this.inativo = false;
        this.tipo = Tipo.VALOR_FIXO;
    }

    public Paciente(String nome,
                    String endereco,
                    Quantidade quantidadeDeDiasNoMes,
                    Moeda valorPorSessao) {
        validarCamposObrigatorios(nome, endereco, quantidadeDeDiasNoMes, valorPorSessao);
        this.nome = nome;
        this.endereco = endereco;
        this.dataDeInicio = LocalDate.now();
        this.quantidadeDeDiasNoMes = quantidadeDeDiasNoMes;
        this.valorPorSessao = valorPorSessao;
        this.inativo = false;
        this.tipo = Tipo.VALOR_POR_SESSAO;
    }

    public void alterar(String endereco, Moeda valorPorSessao) {
        validarCamposObrigatorios(endereco, valorPorSessao);
        this.endereco = endereco;
        this.valorPorSessao = valorPorSessao;
    }

    private void validarCamposObrigatorios(String nome,
                                           String endereco,
                                           Moeda valorPorSessao) {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(nome, "Não é possível criar um paciente sem informar o nome.")
                .quandoNulo(endereco, "Não é possível criar um paciente sem informar o endereço.")
                .quandoNulo(valorPorSessao, "Não é possível criar um paciente sem informar o valor por sessão.")
                .entaoDispara();
    }

    private void validarCamposObrigatorios(String nome,
                                           String endereco,
                                           Quantidade quantidaDeDiasNoMes,
                                           Moeda valorPorSessao) {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(nome, "Não é possível criar um paciente sem informar o nome.")
                .quandoNulo(endereco, "Não é possível criar um paciente sem informar o endereço.")
                .quandoNulo(quantidaDeDiasNoMes, "Não é possível criar um paciente sem quantidade de dias no mes.")
                .quandoNulo(valorPorSessao, "Não é possível criar um paciente sem informar o valor por sessão.")
                .entaoDispara();
    }

    private void validarCamposObrigatorios(String endereco,
                                           Moeda valorPorSessao) {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(endereco, "Não é possível alterar um paciente sem informar o endereço.")
                .quandoNulo(valorPorSessao, "Não é possível alterar um paciente sem informar o valor por sessão.")
                .entaoDispara();
    }

    public void inativar() {
        if (this.getInativo().equals(Boolean.TRUE)) {
            throw new ExcecaoDeRegraDeNegocio("O paciente já está inativo.");
        }
        this.inativo = true;
    }

    public void ativar() {
        if (this.getInativo().equals(Boolean.FALSE)) {
            throw new ExcecaoDeRegraDeNegocio("O paciente já está ativo.");
        }
        this.inativo = false;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public Quantidade getQuantidadeDeDiasNoMes() {
        return quantidadeDeDiasNoMes;
    }

    public Moeda getValorPorSessao() {
        return valorPorSessao;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String obterDescricaoDoTipo() {
        return getTipo().getDescricao();
    }
}
