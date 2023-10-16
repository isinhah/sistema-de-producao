package entity;

import java.sql.Date;

public class OrdemProducao {

  // Atributos
  private int id;
  private String produto;
  private int quantidade;
  private Date dataEntrega;
  private String status;

  // Construtor
  public OrdemProducao(int id, String produto, int quantidade, Date dataEntrega, String status) {
    this.id = id;
    this.produto = produto;
    this.quantidade = quantidade;
    this.dataEntrega = dataEntrega;
    this.status = status;
  }

  // Getters e setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProduto() {
    return produto;
  }

  public void setProduto(String produto) {
    this.produto = produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return " ID: " + id + ", Produto: " + produto + ", Quantidade: " + quantidade + ", Data de Entrega: " + dataEntrega
        + ", Status: " + status;
  }
}
