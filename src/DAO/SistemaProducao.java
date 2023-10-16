package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.OrdemProducao;

public class SistemaProducao {

  // Configuração de conexão com o banco de dados mysql
  private static final String URL = "jdbc:mysql://localhost:3306/sistema_de_producao";
  private static final String USUARIO = "root";
  private static final String SENHA = "1234";

  // Método para registrar uma nova ordem de produção
  public void registrarOrdemProducao(String produto, int quantidade, Date dataEntrega) {
    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
      String sql = "INSERT INTO ordens_producao (produto, quantidade, data_entrega, status) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, produto);
      stmt.setInt(2, quantidade);
      stmt.setDate(3, dataEntrega);
      stmt.setString(4, "pendente");
      stmt.executeUpdate();
      System.out.println("Ordem de produção registrada com sucesso.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Método para listar todas as ordens de produção
  public List<OrdemProducao> listarOrdensProducao() {
    List<OrdemProducao> ordens = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
      String sql = "SELECT * FROM ordens_producao";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        OrdemProducao ordem = new OrdemProducao(
            rs.getInt("id"),
            rs.getString("produto"),
            rs.getInt("quantidade"),
            rs.getDate("data_entrega"),
            rs.getString("status"));
        ordens.add(ordem);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ordens;
  }

  // Verificar produção com base nos materiais disponíveis
  public boolean verificarProducao(String produto, int quantidadeDesejada) {
    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
      String sql = "SELECT status, quantidade FROM ordens_producao WHERE produto = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, produto);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String status = rs.getString("status");
        int quantidadeDisponivel = rs.getInt("quantidade");

        if (status.equals("concluida") && quantidadeDesejada <= quantidadeDisponivel) {
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  // Atualizar o status de uma ordem de produção
  public void atualizarStatus(int idOrdem, String novoStatus) {
    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
      String sql = "UPDATE ordens_producao SET status = ? WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, novoStatus);
      stmt.setInt(2, idOrdem);
      stmt.executeUpdate();
      System.out.println("Status atualizado com sucesso.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Gera relatórios de produção (ordens concluidas e pendente)
  public void gerarRelatorioProducao() {
    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
      String sql = "SELECT * FROM ordens_producao ORDER BY status DESC";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String produto = rs.getString("produto");
        int quantidade = rs.getInt("quantidade");
        Date dataEntrega = rs.getDate("data_entrega");
        String status = rs.getString("status");

        System.out.println("ID: " + id + ", Produto: " + produto + ", Quantidade: " + quantidade + ", Data de Entrega: "
            + dataEntrega + ", Status: " + status);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
