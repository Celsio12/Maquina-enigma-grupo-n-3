#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

const int PIN_LED_VERMELHO = 13;
const int PIN_LED_AMARELO   = 12;
const int PIN_LED_VERDE     = 11;
const int PIN_BUZZER        = 10;

const char WIRING_I[]     = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
const char WIRING_II[]    = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
const char WIRING_III[]   = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
const char NOTCH_I        = 'Q';
const char NOTCH_II       = 'E';
const char NOTCH_III      = 'V';
const char REFLECTOR_B[]  = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

int posE = 0, posM = 0, posD = 0;
int ringE = 0, ringM = 0, ringD = 0;
bool configurando = true;

int mod26(int x) {
  return ((x % 26) + 26) % 26;
}

int rotorForward(const char* wiring, int pos, int ring, int c) {
  int shifted = mod26(c + pos - ring);
  int mapped = wiring[shifted] - 'A';
  return mod26(mapped - pos + ring);
}

int rotorBackward(const char* wiring, int pos, int ring, int c) {
  int shifted = mod26(c + pos - ring);
  int mapped = -1;
  for (int i = 0; i < 26; i++) {
    if (wiring[i] - 'A' == shifted) {
      mapped = i;
      break;
    }
  }
  return mod26(mapped - pos + ring);
}

bool atNotch(int pos, char notch) {
  return (char)('A' + pos) == notch;
}

void avancarRotores() {
  bool meioNoEntalhe = atNotch(posM, NOTCH_II);
  bool direitoNoEntalhe = atNotch(posD, NOTCH_III);

  if (meioNoEntalhe) {
    posE = mod26(posE + 1);
    posM = mod26(posM + 1);
  } else if (direitoNoEntalhe) {
    posM = mod26(posM + 1);
  }
  posD = mod26(posD + 1);
}

char cifrarLetra(char letra) {
  avancarRotores();

  int c = letra - 'A';
  c = rotorForward(WIRING_III, posD, ringD, c);
  c = rotorForward(WIRING_II, posM, ringM, c);
  c = rotorForward(WIRING_I, posE, ringE, c);
  c = REFLECTOR_B[c] - 'A';
  c = rotorBackward(WIRING_I, posE, ringE, c);
  c = rotorBackward(WIRING_II, posM, ringM, c);
  c = rotorBackward(WIRING_III, posD, ringD, c);

  return (char)('A' + c);
}

void mostrarEstadoRotores() {
  lcd.setCursor(0, 1);
  lcd.print("Rot: ");
  lcd.print((char)('A' + posE));
  lcd.print((char)('A' + posM));
  lcd.print((char)('A' + posD));
  lcd.print("      ");
}

void mostrarMenuConfiguracao() {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("A/B/C=rotor D=OK");
  mostrarEstadoRotores();
}

void processarConfiguracao(char comando) {
  switch (comando) {
    case 'A':
      posE = mod26(posE + 1);
      mostrarEstadoRotores();
      break;
    case 'B':
      posM = mod26(posM + 1);
      mostrarEstadoRotores();
      break;
    case 'C':
      posD = mod26(posD + 1);
      mostrarEstadoRotores();
      break;
    case 'D':
      configurando = false;
     
      digitalWrite(PIN_LED_AMARELO, HIGH);
      tone(PIN_BUZZER, 1500, 300);
      delay(300);
      digitalWrite(PIN_LED_AMARELO, LOW);

      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print("Digite Palavra");
      mostrarEstadoRotores();
      Serial.println(F("Configuracao aceita. Modo texto ativado."));
      break;
    default:
     
      digitalWrite(PIN_LED_VERMELHO, HIGH);
      tone(PIN_BUZZER, 400, 150);
      delay(150);
      digitalWrite(PIN_LED_VERMELHO, LOW);
      break;
  }
}

void processarCifragem(String entrada) {
  entrada.toUpperCase();
  String palavraCifrada = "";
 
  for (unsigned int i = 0; i < entrada.length(); i++) {
    char caracterAtual = entrada.charAt(i);
   
    if (caracterAtual >= 'A' && caracterAtual <= 'Z') {
     
      digitalWrite(PIN_LED_VERDE, HIGH);
      tone(PIN_BUZZER, 1000, 50);
     
      palavraCifrada += cifrarLetra(caracterAtual);
     
      delay(50);
      digitalWrite(PIN_LED_VERDE, LOW);
    } else if (caracterAtual == ' ') {
      palavraCifrada += ' ';
    }
  }
 
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print(entrada.substring(0, 16));  
  lcd.setCursor(0, 1);
  lcd.print(palavraCifrada.substring(0, 16));
 
  Serial.print(F("Original:      "));
  Serial.println(entrada);
  Serial.print(F("Criptografado: "));
  Serial.println(palavraCifrada);
  Serial.print(F("Posicao final dos rotores: "));
  Serial.print((char)('A' + posE));
  Serial.print((char)('A' + posM));
  Serial.println((char)('A' + posD));
  Serial.println(F("-----------------------------"));
}

void setup() {
  Serial.begin(9600);
 
  pinMode(PIN_LED_VERMELHO, OUTPUT);
  pinMode(PIN_LED_AMARELO, OUTPUT);
  pinMode(PIN_LED_VERDE, OUTPUT);
  pinMode(PIN_BUZZER, OUTPUT);
 
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("ENIGMA - TFI");
  lcd.setCursor(0, 1);
  lcd.print("3 rotores I-II-III");
 
  tone(PIN_BUZZER, 1200, 100);
  delay(100);
  tone(PIN_BUZZER, 1800, 100);
 
  delay(1800);
  mostrarMenuConfiguracao();
}

void loop() {
  if (Serial.available() > 0) {
    String entrada = Serial.readStringUntil('\n');
    entrada.trim();
   
    if (entrada.length() > 0) {
      if (configurando) {
        entrada.toUpperCase();
        processarConfiguracao(entrada.charAt(0));
      } else {
        processarCifragem(entrada);
      }
    }
  }
}
