# AlgLinComp-UFRJ-2021
Repositório com as implementações dos métodos aprendidos em aula. <br />
**O programa necessita do Java instalado. Caso não possua o java no seu computador ou tenha problemas de compatibilidade, instale o JDK em https://www.oracle.com/java/technologies/javase-jdk11-downloads.html**

O programa 1 gerado é capaz de resolver sistemas lineares quadrados da forma Ax=b utilizando os quatro métodos aprendidos em aula: 
- Decomposição LU - permite cálculo do determinante
- Decomposição de Cholesky - permite cálculo do determinante
- Procedimento Iterativo Jacobi
- Procedimento Iterativo Gauss-Seidel

Os inputs gerais do programa são:
- A ordem da matriz A a ser analisada
- O método de solução desejado
- O caminho para o arquivo txt contendo a matriz A e o vetor b a serem analisados
O caminho para o arquivo txt onde os outputs do programa serão escritos, como a descrição da matriz A e vetor b lidos, determinante (se requisitado e possível) e vetor resposta, por exemplo

Para que o programa funcione, basta preencher as entradas corretamente e pressionar o botão "pronto" na guia "dados de saída".

OBS: 
- Para requisitar o determinante da matriz A, preencha o campo "Determinante" com a palavra "sim" (sem aspas).
- O programa espera um "." para ler casas decimais. Por exemplo, escreva "0.5" e não "0,5" (sem aspas).
- É recomendado preencher o caminho completo para os arquivos de entrada e saida. Exemplo: "C:\Users\Myuser\Desktop\teste_entrada.txt".
- Há exemplos de matriz de entrada (arquivo entrada_exemplo.txt) e arquivos de sáida produzidas pelo programa (saida_lu.txt, saida_cholesky.txt, saida_jacobi.txt, saida_gauss_seidel.txt).
- Exemplo de preenchimento de arquivo txt para entrada: Os primeiros elementos representam a matriz A, enquanto os próximos elementos representam o vetor b:

3 -1 -1.5 <br />
-1 3.9 -1 <br />
-1.5 -1 3 <br />

1 2 1 <br />

===================================================================

**O programa 2** gerado é capaz de calcular autovalores e autovetores de matrizes quadradas utilizando os dois métodos aprendidos em aula:
- Método da Potência
- Método de Jacobi

Os inputs gerais do programa são:
- A ordem da matriz A a ser analisada
- O método de solução desejado
- O caminho para o arquivo txt contendo a matriz A a ser analisada.

O caminho para o arquivo txt onde os outputs do programa serão escritos, como a descrição da matriz A lida, autovalores e autovetores.

Para que o programa funcione, basta preencher as entradas corretamente e pressionar o botão "pronto" na guia "dados de saída". 

OBS:
- O programa espera um "." para ler casas decimais.  Por exemplo, escreva "0.5" e não "0,5" (sem aspas).
- É recomendado preencher o caminho completo para os arquivos  de entrada e saida. Exemplo: "C:\Users\Myuser\Desktop\teste_entrada.txt"
- Exemplo de preenchimento de arquivo txt para entrada representando a matriz A:

3 1 -1
1 3 1.1
1 4 3
