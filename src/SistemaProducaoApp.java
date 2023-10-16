import java.util.Scanner;

import DAO.SistemaProducao;

public class SistemaProducaoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaProducao sistemaDAO = new SistemaProducao();

        // Menu para o usuário
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Registrar Ordem de Produção");
            System.out.println("2. Listar Ordens de Produção");
            System.out.println("3. Verificar Produção");
            System.out.println("4. Atualizar Status de Ordem de Produção");
            System.out.println("5. Gerar Relatório de Produção");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do produto:");
                    String produto = scanner.nextLine();

                    System.out.println("Digite a quantidade desejada:");
                    int quantidade = scanner.nextInt();

                    System.out.println("Digite a data de entrega (AAAA-MM-DD):");
                    String dataEntregaStr = scanner.next();
                    java.sql.Date dataEntrega = java.sql.Date.valueOf(dataEntregaStr);

                    sistemaDAO.registrarOrdemProducao(produto, quantidade, dataEntrega);
                    break;
                case 2:
                    System.out.println("Lista de Ordens de Produção:");
                    sistemaDAO.listarOrdensProducao().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Digite o nome do produto:");
                    String produtoVerificar = scanner.nextLine();

                    System.out.println("Digite a quantidade desejada:");
                    int quantidadeVerificar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    boolean producaoPossivel = sistemaDAO.verificarProducao(produtoVerificar, quantidadeVerificar);

                    if (producaoPossivel) {
                        System.out.println("Produção possível");
                    } else {
                        System.out.println("Produção não é possível devido à falta de materiais");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ID da Ordem de Produção:");
                    int idOrdem = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o novo status (pendente ou concluida):");
                    String novoStatus = scanner.nextLine();

                    sistemaDAO.atualizarStatus(idOrdem, novoStatus);
                    break;
                case 5:
                    System.out.println("Relatório de Produção:");
                    sistemaDAO.gerarRelatorioProducao();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
