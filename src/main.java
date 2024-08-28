import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int quantidade_alunos;

        System.out.print("Defina a quantidade de alunos: ");
        quantidade_alunos = scan.nextInt();
        scan.nextLine();

        String[] cabecalho = {"NOME", "N1", "N2", "MÉDIA", "STATUS"};

        String[] nomes = new String[quantidade_alunos];
        float[][] matrizNOTAS = new float[quantidade_alunos][3];
        String[] resultado = new String[quantidade_alunos];

        for (int i = 0; i < quantidade_alunos; i++) {
            System.out.print("Digite o nome do aluno: ");
            nomes[i] = scan.nextLine();

            System.out.print("Digite a nota 1: ");
            float nota1 = scan.nextFloat();

            System.out.print("Digite a nota 2: ");
            float nota2 = scan.nextFloat();
            scan.nextLine();

            float media = (nota1 + nota2) / 2;
            String resultadoFINAL = media >= 6.0f ? "APROVADO" : "REPROVADO";

            matrizNOTAS[i][0] = nota1;
            matrizNOTAS[i][1] = nota2;
            matrizNOTAS[i][2] = media;
            resultado[i] = resultadoFINAL;
        }

        try (FileWriter writer = new FileWriter("tabela_alunos.html")) {
            writer.write("<!DOCTYPE html>\n<html lang=\"pt-BR\">\n<head>\n");
            writer.write("<meta charset=\"UTF-8\">\n<title>Tabela de Alunos</title>\n");
            writer.write("<link rel=\"stylesheet\" href=\"style.css\">\n");
            writer.write("</head>\n<body>\n");
            writer.write("<h1>Dados dos Alunos</h1>\n");
            writer.write("<table>\n<tr>");

            for (String coluna : cabecalho) {
                writer.write("<th>" + coluna + "</th>");
            }
            writer.write("</tr>\n");

            for (int i = 0; i < quantidade_alunos; i++) {
                writer.write("<tr>");
                writer.write("<td>" + nomes[i] + "</td>");
                writer.write("<td>" + matrizNOTAS[i][0] + "</td>");
                writer.write("<td>" + matrizNOTAS[i][1] + "</td>");
                writer.write("<td>" + matrizNOTAS[i][2] + "</td>");
                writer.write("<td class=\"" + (resultado[i].equals("APROVADO") ? "aprovado" : "reprovado") + "\">" + resultado[i] + "</td>");
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
