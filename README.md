# Máquina Enigma — Grupo Nº 3

Este projeto é uma implementação funcional da famosa **Máquina Enigma**, o dispositivo eletromecânico de criptografia utilizado para proteger comunicações comerciais e militares durante a primeira metade do século XX, alcançando maior notoriedade durante a Segunda Guerra Mundial.

O objetivo deste projeto é simular com precisão o comportamento mecânico e lógico da máquina, permitindo tanto a cifragem quanto a decifragem de mensagens textuais, através de um simulador em Java e de um protótipo físico em Arduino.

---

## Funcionalidades

* **Configuração de Rotores:** escolha e posicionamento dos rotores (I, II e III) a partir do catálogo original.
* **Definição da Posição Inicial (*Ringstellung*):** ajuste do alinhamento inicial de cada rotor antes de iniciar a mensagem.
* **Painel de Conexões (*Steckerbrett*):** permite a troca de pares de letras antes e depois de passarem pelos rotores, aumentando exponencialmente a segurança.
* **Refletor (*Umkehrwalze*):** garante a propriedade de reciprocidade da máquina (a mesma configuração cifra e decifra).
* **Mecanismo de Avanço Automático:** simulação do avanço físico (*stepping*) dos rotores a cada tecla pressionada, incluindo o efeito de "duplo passo" (*double-stepping*).

---

## Tecnologias Utilizadas

* **Linguagem Principal:** Java
* **Protótipo Físico:** Arduino (C/C++), com teclado matricial 4x4 e ecrã LCD I2C

---

## Estrutura do Projeto

A lógica do sistema está dividida nos seguintes módulos principais:

* **Rotor:** controla a fiação interna de substituição de cada rotor, a sua posição atual e o momento em que atinge o "entalhe" (*notch*) para girar o rotor seguinte.
* **Reflector:** realiza a inversão do sinal para retornar pelos rotores.
* **Plugboard:** gerencia o mapeamento e a troca de pares de letras.
* **Enigma:** o controlador central que conecta todas as peças e processa o fluxo de cada caractere.
* **Configuracao:** guarda os parâmetros da chave (ordem dos rotores, posições, anéis, refletor, plugboard).
* **EnigmaController:** faz a ponte entre a configuração e a máquina, incluindo os testes automáticos.

---

## Como Executar o Projeto

### Pré-requisitos
Certifique-se de ter o **JDK (Java Development Kit)** instalado em sua máquina.

### Passo a Passo

**1. Clone o repositório:**
```bash
git clone https://github.com/Celsio12/Maquina-enigma-grupo-n-3.git
```

**2. Aceda à pasta do projeto:**
```bash
cd Maquina-enigma-grupo-n-3/src
```

**3. Compile e execute:**
```bash
javac -d ../bin controller/*.java model/*.java util/*.java Main.java
cd ../bin
java Main
```

---

## Exemplo de Uso

Como a criptografia Enigma é simétrica, se configurar a máquina exatamente da mesma forma, a mensagem cifrada transforma-se novamente no texto original.

1. Rotores: `I - II - III`
2. Posições iniciais: `AAA`
3. Refletor: `B`
4. Mensagem original: `AAAAA`
5. Mensagem cifrada gerada: `BDZGO`

Ao inserir `BDZGO` com a **mesma configuração**, o resultado será `AAAAA`.

---

## Protótipo Físico (Arduino)

O protótipo físico replica a mesma lógica de cifragem em C/C++, utilizando um teclado matricial 4x4 para introduzir as letras (código 01–26 + `#`) e um ecrã LCD 16x2 I2C para mostrar o resultado. Ver `arduino/enigma_arduino.ino` para o código completo e instruções de ligação.

---

## Integrantes do Grupo Nº 3

* **Amélia Kalende**
* **Célsio Gaspar**
* **Lycia Paulo**

---

Desenvolvido como projeto académico para a unidade curricular de Matemática Discreta & Lógica.
