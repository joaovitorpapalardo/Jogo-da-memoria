package v1;

import java.io.*;
import java.util.*;

public class Placares {
    private static final String NOME_ARQUIVO = "placares.csv";

    public static void salvarPlacar(String nomeJogador, int tentativas) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            escritor.write(nomeJogador + "," + tentativas);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> carregarTop10Placares() {
        List<String[]> placares = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    placares.add(partes);
                }
            }
        } catch (IOException e) {
            // arquivo pode não existir ainda
        }

        placares.sort(Comparator.comparingInt(p -> Integer.parseInt(p[1])));
        return placares.size() > 10 ? placares.subList(0, 10) : placares;
    }
}
