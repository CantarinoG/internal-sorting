
/** ************************************************
 * Aluno: Guilherme Alvim Cantarino
 * Matrícula: 2021008425
 * Curso: Ciência da Computação
 * 1° Trabalho Prático -- Ordenação Interna
 * DCC05288 -- 2022 -- IFSEMG, 3°
 * Prof. Flávio Augusto de Freitas
 * Compilador: javac
 * Sistema Operacional: Windows 11 Home Single Language
 ************************************************ */
public class tp1I2021008425 {

    //Essas variáveis guardam estatísticas do uso de cada algoritmo:
    static long acessos = 0;
    static long comparacoes = 0;
    static long trocas = 0;
    static long insercoes = 0;
    static long pivos = 0;
    static double tempoInicial = 0;
    static double tempoFinal = 0;

    static long dados[] = new long[33];
    static double dadosTempo[] = new double[9];

    /*A função zeraEstatisticas a seguir serve para zerar os valores de estatísticas do uso de algoritmos, 
    para que elas sejam resetadas e possam ser usadas novamente para armazenar as estatísticas de outro algoritmo:*/
    static void zeraEstatisticas() {
        acessos = 0;
        comparacoes = 0;
        trocas = 0;
        insercoes = 0;
        pivos = 0;
    }

    //A função imprimeVetor a seguir imprimir na tela os primeiros n elementos de um vetor:
    static void imprimeVetor(int vetor[], int n) {
        System.out.println("Os " + n + " primeiros elementos do vetor se encontram dispostos da seguinte forma:");
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + ", ");
        }
        System.out.println("\b\b]");
    }

    //A função ordenaVetorInsertionSort a seguir ordena os primeiros n elemento de um vetor usando o algoritmo de ordenação insertion sort:
    static void ordenaVetorInsertionSort(int vetor[], int n) {
        for (int i = 0; i < n; i++) {
            int elementoSelecionado = vetor[i];
            int elementoFinal = i - 1;
            acessos++;
            insercoes++;
            while (elementoFinal >= 0 && elementoSelecionado <= vetor[elementoFinal]) {
                vetor[elementoFinal + 1] = vetor[elementoFinal];
                elementoFinal -= 1;
                acessos += 3;
                comparacoes++;
                insercoes++;
                trocas++;
            }
            vetor[elementoFinal + 1] = elementoSelecionado;
            acessos++;
            trocas++;
        }
    }

    //A função merge a seguir é usada para combinar dois vetores em um de forma ordenada, e é usada dentro do merge sort:
    static void merge(int[] vetor, int[] vetorEsquerdo, int[] vetorDireito, int elementoEsquerdo, int elementoDireito) {
        int i = 0, j = 0, k = 0;

        while (i < elementoEsquerdo && j < elementoDireito) {
            if (vetorEsquerdo[i] <= vetorDireito[j]) {
                vetor[k++] = vetorEsquerdo[i++];
                acessos += 4;
                comparacoes += 2;
                trocas++;
            } else {
                vetor[k++] = vetorDireito[j++];
                acessos += 4;
                trocas++;
            }
        }
        while (i < elementoEsquerdo) {
            vetor[k++] = vetorEsquerdo[i++];
            acessos += 2;
            trocas++;
        }

        while (j < elementoDireito) {
            vetor[k++] = vetorDireito[j++];
            acessos += 2;
            trocas++;
        }
    }

    //A função ordenaVetorMergeSort a seguir ordena os primeiros n elemento de um vetor usando o algoritmo de ordenação merge sort:
    static void ordenaVetorMergeSort(int vetor[], int n) {
        if (n < 2) {
            return;
        }
        int meio = n / 2;
        int[] esquerda = new int[meio];
        int[] direita = new int[n - meio];

        for (int i = 0; i < meio; i++) {
            esquerda[i] = vetor[i];
            acessos += 2;
            trocas++;
        }

        for (int i = meio; i < n; i++) {
            direita[i - meio] = vetor[i];
            acessos += 2;
            trocas++;
        }

        ordenaVetorMergeSort(esquerda, meio);
        ordenaVetorMergeSort(direita, n - meio);

        merge(vetor, esquerda, direita, meio, n - meio);
    }

    //A função particao a seguir serve para fazer a partição de um vetor e é usada dentro do quick sort:
    static int particao(int vetor[], int inicio, int fim) {
        int pivo = vetor[fim];
        int i = (inicio - 1);
        acessos++;
        pivos++;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                int aux = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = aux;
                acessos += 5;
                comparacoes++;
                trocas += 2;
            }
        }

        int aux = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = aux;
        acessos += 4;
        trocas += 2;
        return i + 1;
    }

    //A função ordenaVetorQuickSort a seguir ordena os primeiros n elemento de um vetor usando o algoritmo de ordenação quick sort:
    static void ordenaVetorQuickSort(int vetor[], int inicio, int fim) {
        if (inicio < fim) {
            int indiceParticao = particao(vetor, inicio, fim);
            ordenaVetorQuickSort(vetor, inicio, indiceParticao - 1);
            ordenaVetorQuickSort(vetor, indiceParticao + 1, fim);
        }
    }

    /*A função imprimeResultados a seguir serve para imprimir, de forma organizada, informações técnicas a respeito das ordenações efetuadas
    nesse programa, de modo a facilitar a comparação da eficiência de cada um dos algoritmos de ordenação: */
    static void imprimeResultados() {
        System.out.printf("                   ORDENACAO                    |                                                    VETOR                      \n");
        System.out.printf("------------------------------------------------|-------------------------------------------------------------------------------------------------------------\n");
        System.out.printf("    METODO              TEMPO TOTAL(ms)         |                ACESSOS              |             COMPARACOES            |                TROCAS\n");
        System.out.printf("                5000        10000       30000   |     5000        10000       30000   |    5000        10000       30000   |    5000        10000       30000   \n");
        System.out.printf(" ===========  ==========  ==========  ==========|============  ==========  ========== | ==========  ==========  ========== | ==========  ==========  ========== \n");
        System.out.printf("INSERTION   %12.1f%12.1f%12.1f|%12d%12d%12d|%12d%12d%12d |%12d%12d%12d (%9d/%9d/%9d insercoes)\n", dadosTempo[0], dadosTempo[1], dadosTempo[2], dados[0], dados[4], dados[8], dados[1], dados[5], dados[9], dados[2], dados[6], dados[10], dados[3], dados[7], dados[11]);
        System.out.printf("MERGE       %12.1f%12.1f%12.1f|%12d%12d%12d|%12d%12d%12d |%12d%12d%12d\n", dadosTempo[3], dadosTempo[4], dadosTempo[5], dados[12], dados[15], dados[18], dados[13], dados[16], dados[19], dados[14], dados[17], dados[20]);
        System.out.printf("QUICK       %12.1f%12.1f%12.1f|%12d%12d%12d|%12d%12d%12d |%12d%12d%12d (%6d/%6d/%6d pivos selecionados)\n", dadosTempo[6], dadosTempo[7], dadosTempo[8], dados[21], dados[25], dados[29], dados[22], dados[26], dados[30], dados[23], dados[27], dados[31], dados[24], dados[28], dados[32]);
    }

    public static void main(String[] args) {

        //Declaração do vetor de 30000 inteiros, com valores aleatórios que são gerados cada vez que o programa é executado
        int[] vetorOriginal = new int[30000];

        //Inicialização do vetor de 30000 inteiros, com 30000 valores aleatórios entre 0 e 1000 que são gerados cada vez que o programa é executado 
        for (int i = 0; i < 30000; i++) {
            vetorOriginal[i] = (int) (Math.random() * 1000);
        }

        //Aqui são feitas algumas deepcopys do vetor original, para que essas possam ser ordenadas usando diferentes algoritmos:
        int[] vetor20A = new int[20];
        System.arraycopy(vetorOriginal, 0, vetor20A, 0, 20);
        int[] vetor20B = new int[20];
        System.arraycopy(vetor20A, 0, vetor20B, 0, 20);
        int[] vetor20C = new int[20];
        System.arraycopy(vetor20A, 0, vetor20C, 0, 20);
        int[] vetor5000A = new int[5000];
        System.arraycopy(vetorOriginal, 0, vetor5000A, 0, 5000);
        int[] vetor5000B = new int[5000];
        System.arraycopy(vetorOriginal, 0, vetor5000B, 0, 5000);
        int[] vetor5000C = new int[5000];
        System.arraycopy(vetorOriginal, 0, vetor5000C, 0, 5000);
        int[] vetor10000A = new int[10000];
        System.arraycopy(vetorOriginal, 0, vetor10000A, 0, 10000);
        int[] vetor10000B = new int[10000];
        System.arraycopy(vetorOriginal, 0, vetor10000B, 0, 10000);
        int[] vetor10000C = new int[10000];
        System.arraycopy(vetorOriginal, 0, vetor10000C, 0, 10000);
        int[] vetor30000A = new int[30000];
        System.arraycopy(vetorOriginal, 0, vetor30000A, 0, 30000);
        int[] vetor30000B = new int[30000];
        System.arraycopy(vetorOriginal, 0, vetor30000B, 0, 30000);
        int[] vetor30000C = new int[30000];
        System.arraycopy(vetorOriginal, 0, vetor30000C, 0, 30000);

        //Demonstração da execução do insertion, merge e quick sort, com 20 elementos cada:
        System.out.println("******************************ORDENACAO COM INSERTION SORT******************************\n");
        System.out.println("PRE-ORDENACAO: ");
        imprimeVetor(vetor20A, 20);
        ordenaVetorInsertionSort(vetor20A, 20);
        System.out.println("\nPOS-ORDENACAO: ");
        imprimeVetor(vetor20A, 20);

        System.out.println("\n\n******************************ORDENACAO COM MERGE SORT******************************\n");
        System.out.println("PRE-ORDENACAO: ");
        imprimeVetor(vetor20B, 20);
        ordenaVetorMergeSort(vetor20B, 20);
        System.out.println("\nPOS-ORDENACAO: ");
        imprimeVetor(vetor20B, 20);

        System.out.println("\n\n******************************ORDENACAO COM QUICK SORT******************************\n");
        System.out.println("PRE-ORDENACAO: ");
        imprimeVetor(vetor20C, 20);
        ordenaVetorQuickSort(vetor20C, 0, 19);
        System.out.println("\nPOS-ORDENACAO: ");
        imprimeVetor(vetor20C, 20);

        System.out.println("\n\n****************************EXECUTANDO DEMAIS ORDENACOES****************************\n");
        //Obtenção das estatísticas das ordenações para impressão em relatório final:
        //Execução Insertion Sort 5000 elementos:
        zeraEstatisticas();
        System.out.print("\nExecutando Insertion Sort com 5000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorInsertionSort(vetor5000A, 5000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[0] = tempoFinal;
        dados[0] = acessos;
        dados[1] = comparacoes;
        dados[2] = trocas;
        dados[3] = insercoes;

        //Execução Insertion Sort 10000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Insertion Sort com 10000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorInsertionSort(vetor10000A, 10000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[1] = tempoFinal;
        dados[4] = acessos;
        dados[5] = comparacoes;
        dados[6] = trocas;
        dados[7] = insercoes;

        //Execução Insertion Sort 30000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Insertion Sort com 30000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorInsertionSort(vetor30000A, 30000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[2] = tempoFinal;
        dados[8] = acessos;
        dados[9] = comparacoes;
        dados[10] = trocas;
        dados[11] = insercoes;

        //Execução Merge Sort 5000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Merge Sort com 5000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorMergeSort(vetor5000B, 5000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[3] = tempoFinal;
        dados[12] = acessos;
        dados[13] = comparacoes;
        dados[14] = trocas;

        //Execução Merge Sort 10000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Merge Sort com 10000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorMergeSort(vetor10000B, 10000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[4] = tempoFinal;
        dados[15] = acessos;
        dados[16] = comparacoes;
        dados[17] = trocas;

        //Execução Merge Sort 30000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Merge Sort com 30000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorMergeSort(vetor30000B, 30000);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[5] = tempoFinal;
        dados[18] = acessos;
        dados[19] = comparacoes;
        dados[20] = trocas;

        //Execução Quick Sort 5000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Quick Sort com 5000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorQuickSort(vetor5000C, 0, 4999);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[6] = tempoFinal;
        dados[21] = acessos;
        dados[22] = comparacoes;
        dados[23] = trocas;
        dados[24] = pivos;

        //Execução Quick Sort 10000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Quick Sort com 10000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorQuickSort(vetor10000C, 0, 9999);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[7] = tempoFinal;
        dados[25] = acessos;
        dados[26] = comparacoes;
        dados[27] = trocas;
        dados[28] = pivos;

        //Execução Quick Sort 30000 elementos;
        zeraEstatisticas();
        System.out.print("\nExecutando Quick Sort com 30000 elementos...");
        tempoInicial = System.currentTimeMillis();
        ordenaVetorQuickSort(vetor30000C, 0, 29999);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("   FEITO!");
        dadosTempo[8] = tempoFinal;
        dados[29] = acessos;
        dados[30] = comparacoes;
        dados[31] = trocas;
        dados[32] = pivos;

        //Impressão do relatório final na tela:
        System.out.println("\n\n******************************RELATORIO FINAL******************************\n");
        imprimeResultados();

    }
}
