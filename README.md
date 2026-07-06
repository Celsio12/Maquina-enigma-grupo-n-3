
#  Máquina Enigma - Grupo N° 3

Este projeto é uma implementação funcional da famosa **Máquina Enigma**, o dispositivo eletromecânico de criptografia utilizado para proteger as comunicações comerciais e militares durante a primeira metade do século XX, alcançando maior notoriedade durante a Segunda Guerra Mundial.

O objetivo deste projeto é simular com precisão o comportamento mecânico e lógico da máquina, permitindo tanto a cifragem quanto a decifragem de mensagens textuais.

---

##  Funcionalidades

* **Configuração de Rotores:** Escolha e posicionamento dos rotores (geralmente I, II e III) a partir do catálogo original.
* **Definição da Posição Inicial (*Ringstellung*):** Ajuste do alinhamento inicial de cada rotor antes de iniciar a mensagem.
* **Painel de Conexões (*Steckerbrett*):** Permite a troca de pares de letras antes e depois de passarem pelos rotores, aumentando exponencialmente a segurança.
* **Refletor (*Umkehrwalze*):** Garante a propriedade de reciprocidade da máquina (o mesmo processo e configuração servem para cifrar e decifrar).
* **Mecanismo de Avanço Automático:** Simulação do avanço físico (*stepping*) dos rotores a cada tecla pressionada, incluindo o efeito de "duplo passo" (*double-stepping*) se aplicável.

---

##  Tecnologias Utilizadas

* **Linguagem Principal:** Java, C++
* **Interface:** Arduino

---

##  Estrutura do Projeto

A lógica do sistema está dividida nos seguintes módulos principais:

* **Plugboard:** Gerencia o mapeamento e a troca de pares de letras.
* **Rotor:** Controla a fiação interna de substituição de cada rotor, sua posição atual e o momento em que atinge o "entalhe" (*notch*) para girar o próximo rotor.
* **Reflector:** Realiza a inversão do sinal elétrico para retornar pelos rotores.
* **EnigmaMachine:** O controlador central que conecta todas as peças e processa o fluxo do caractere.

---

## Como Executar o Projeto

### Pré-requisitos
Certifique-se de ter o [Inserir o interpretador/compilador necessário, ex: Python 3.x / Node.js] instalado em sua máquina.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/Celsio12/Maquina-enigma-grupo-n-3.git](https://github.com/Celsio12/Maquina-enigma-grupo-n-3.git)

```

2. **Acesse a pasta do projeto:**
```bash
cd Maquina-enigma-grupo-n-3

```


3. **Execute o programa:**
```bash
# Exemplo para Python (substitua pelo comando correto do seu projeto)
python main.py

```



---

## Exemplo de Uso

Como a criptografia Enigma é simétrica, se você configurar a máquina exatamente da mesma forma, a mensagem cifrada se transformará novamente no texto original.

1. Defina os rotores: `I - II - III`
2. Defina as conexões do painel (*Plugboard*): `A-B, C-D`
3. Mensagem original: `HELLO WORLD`
4. Mensagem cifrada gerada: `[Exemplo: KCHWF ZAMVR]`

Ao inserir `KCHWF ZAMVR` com a **mesma configuração**, o resultado será `HELLO WORLD`.

---

## Integrantes do Grupo N° 3

* **Amélia kalende** 
* **Célsio Gaspar** 
* **Lycia Paulo** 
* 

---

Desenvolvido como projeto acadêmico/prático. 





```
