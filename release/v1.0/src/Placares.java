package v1;

import java.io.*;
import java.util.*;

/**
 * Classe utilitária responsável por salvar e carregar os placares dos jogadores
 * em um arquivo CSV. Os placares são ordenados pelo número de tentativas, do menor para o maior.
 * @author joaovitorpapalardo, joaovitorrodrigues, milenacamposm, nicolasbabo
 * @version 1.0
 * @since 1.0
 */
public class Placares {
    private static final String NOME_ARQUIVO = "placares.csv";
    
    /**
     * Salva um novo placar no arquivo CSV, adicionando o nome do jogador e o número de tentativas.
     *
     * @param nomeJogador o nome do jogador
     * @param tentativas  o número de tentativas que o jogador fez para concluir o jogo
     */
    public static void salvarPlacar(String nomeJogador, int tentativas) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            escritor.write(nomeJogador + "," + tentativas);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carrega os placares do arquivo CSV e retorna os 10 melhores, baseando-se
     * no menor número de tentativas.
     *
     * @return uma lista com até 10 arrays de string, onde cada array representa um placar
     *         no formato [nomeJogador, tentativas]
     */
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
        }

        placares.sort(Comparator.comparingInt(p -> Integer.parseInt(p[1])));
        return placares.size() > 10 ? placares.subList(0, 10) : placares;
    }
}
