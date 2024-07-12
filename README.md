# README.md

## Sistemas Operacionais - Trabalho 2

Este repositório contém a implementação de um simulador de gerenciamento de memória paginada para um sistema operacional. O simulador recebe como entrada uma sequência de endereços virtuais e apresenta como saída os endereços físicos correspondentes. Este trabalho foi realizado como parte da disciplina de Sistemas Operacionais.

### Objetivos

1. Implementar um simulador de um sistema de gerenciamento de memória paginada.
2. Configurar o simulador para aceitar parâmetros de tamanho da memória virtual, tamanho da memória física e tamanho da página.
3. Inicializar a tabela de páginas e a memória física conforme especificado.
4. Mapear endereços virtuais para endereços físicos e lidar com falhas de página.

### Detalhes da Implementação

#### a) Configuração

- Tamanho da memória virtual (em bits, 2^n)
- Tamanho da memória física (em bits, 2^n)
- Tamanho da página (em bits, 2^n)

#### b) Estruturas de Dados

- Tabela de páginas (1 nível)
- Vetor representando a memória física

#### c) Mapeamento

- Referências mapeadas para uma mesma moldura são representadas pela mesma posição do vetor de molduras.

#### d) Inicialização

- A memória física é inicializada com zeros.
- A tabela de páginas é inicializada com valores -1.

#### e) Comportamento em Caso de Falha

- Se a memória física estiver lotada, o programa deve parar e indicar que a memória está cheia.

### Código Fonte

#### Arquivo: `MemoryManagerSimulator.java`


###

 Exemplo de Execução

Abaixo está um exemplo de como a tabela de páginas pode ser construída e os endereços virtuais são mapeados para endereços físicos:

#### Configuração

- Tamanho da Memória Virtual: 16 bits
- Tamanho da Memória Física: 15 bits
- Tamanho da Página: 12 bits

#### Tabela de Páginas

| End (virtual) | Página | Moldura |
|---------------|--------|---------|
| 0k-4k         | 0      | 0       |
| 4k-8k         | 1      | 1       |
| 8k-12k        | 2      | 3       |
| 12k-16k       | 3      | 2       |
| 16k-20k       | 4      | -       |
| 20k-24k       | 5      | -       |
| 24k-28k       | 6      | -       |
| 28k-32k       | 7      | -       |
| 32k-36k       | 8      | -       |
| 36k-40k       | 9      | -       |
| 40k-44k       | 10     | -       |
| 44k-48k       | 11     | -       |
| 48k-52k       | 12     | -       |
| 52k-56k       | 13     | -       |
| 56k-60k       | 14     | -       |
| 60k-64k       | 15     | 4       |

#### Tabela de Páginas Invertida

| End (físico)  | Moldura | Página |
|---------------|---------|--------|
| 0k-4k         | 0       | 0      |
| 4k-8k         | 1       | 1      |
| 8k-12k        | 2       | 3      |
| 12k-16k       | 3       | 2      |
| 16k-20k       | 4       | 15     |
| 20k-24k       | 5       | -      |
| 24k-28k       | 6       | -      |
| 28k-32k       | 7       | -      |

Este README fornece uma visão geral do trabalho realizado, explicando as funcionalidades e apresentando exemplos práticos de execução.