import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int def_quantidade;
        System.out.print("Defina a quantidade de alunos.  >>: ");
        def_quantidade = scan.nextInt();
        scan.nextLine();

        String[] cabecalho = {"NOME", "N1", "N2", "MÉDIA", "STATUS"};
        Object[][] dados = new Object[def_quantidade][5];

        for (int i = 0; i < def_quantidade; i++) {
            System.out.print("Digite o nome do aluno: ");
            dados[i][0] = scan.nextLine();

            System.out.print("Digite a nota 1: ");
            float nota1 = scan.nextFloat();

            System.out.print("Digite a nota 2: ");
            float nota2 = scan.nextFloat();
            scan.nextLine(); 

            float media = (nota1 + nota2) / 2;
            String status = media >= 6.0f ? "APROVADO" : "REPROVADO";

            dados[i][1] = nota1;
            dados[i][2] = nota2;
            dados[i][3] = media;
            dados[i][4] = status;
        }

        try (FileWriter writer = new FileWriter("tabela_alunos.html")) {
            writer.write("<!DOCTYPE html>\n<html lang=\"pt-BR\">\n<head>\n");
            writer.write("<meta charset=\"UTF-8\">\n<title>Tabela de Alunos</title>\n");
            writer.write("<link rel=\"stylesheet\" href=\"style.css\">\n");
            writer.write("</head>\n<body>\n");
            writer.write("<h1>Dados dos Alunos</h1>\n");
            writer.write("<table>\n<tr>");
            for (String cab : cabecalho) {
                writer.write("<th>" + cab + "</th>");
            }
            writer.write("</tr>\n");

            for (Object[] linha : dados) {
                writer.write("<tr>");
                for (int j = 0; j < linha.length; j++) {
                    if (j == 4) { // Status
                        String status = (String) linha[j];
                        writer.write("<td class=\"" + (status.equals("APROVADO") ? "aprovado" : "reprovado") + "\">" + status + "</td>");
                    } else {
                        writer.write("<td>" + linha[j] + "</td>");
                    }
                }
                writer.write("</tr>\n");
            }
            writer.write("</table>\n");

            writer.write("<a href=\"index.html\"><button type=\"submit\">SAIR</button></a>");
            writer.write("</body>\n</html>");

            System.out.println("#### ARQUIVO HTML GERADO ####");

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
