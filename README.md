# PanelEgzaminy

Aplikacja desktopowa w języku Java do zarządzania i oceniania egzaminów z interfejsem graficznym Swing.

## 📋 Opis projektu

PanelEgzaminy to aplikacja desktopowa stworzona w Javie z wykorzystaniem biblioteki Swing, która umożliwia wczytywanie pytań egzaminacyjnych z plików tekstowych oraz obliczanie wyników testów. System oferuje intuicyjny interfejs graficzny do zarządzania punktacją i analizy wyników egzaminów.

## 🚀 Funkcjonalności

- **Wczytywanie pytań z plików** - import pytań z plików tekstowych w określonym formacie
- **Obliczanie wyników** - automatyczne wyliczanie punktów za egzamin
- **System błędów** - możliwość odejmowania punktów za błędy
- **Punkty dodatkowe** - opcja dodawania dodatkowych punktów
- **Punktacja częściowa** - wsparcie dla częściowego punktowania odpowiedzi
- **Interfejs graficzny** - przejrzysty i nowoczesny interfejs użytkownika

## 🛠️ Technologie

- **Java** - główny język programowania
- **Swing** - interfejs graficzny użytkownika
- **AWT** - dodatkowe komponenty UI
- **UTF-8** - kodowanie plików tekstowych

## 📦 Instalacja i uruchomienie

### Wymagania systemowe

- Java 8 lub nowsza
- System operacyjny: Windows, macOS, Linux

### Kroki instalacji

1. **Sklonuj repozytorium**
   ```bash
   git clone https://github.com/SzpakPoland/PanelEgzaminy.git
   cd PanelEgzaminy
   ```

2. **Kompilacja**
   ```bash
   javac -d out src/*.java src/model/*.java src/service/*.java src/ui/*.java src/utils/*.java
   ```

3. **Uruchomienie**
   ```bash
   java -cp out Main
   ```

### Alternatywnie (jeśli masz skonfigurowane IDE):

1. Otwórz projekt w IntelliJ IDEA, Eclipse lub NetBeans
2. Uruchom klasę `Main.java`

## 📝 Format pliku z pytaniami

Aplikacja wczytuje pytania z plików tekstowych w następującym formacie:

```
Treść pytania pierwszego PMAX: 5
Treść pytania drugiego PMAX: 3
Treść pytania trzeciego PMAX: 2
```

**Wyjaśnienie formatu:**
- Każde pytanie w osobnej linii
- Na końcu linii: `PMAX: X` gdzie X to maksymalna liczba punktów za pytanie
- Kodowanie pliku: UTF-8

**Przykład pliku `pytania.txt`:**
```
Co to jest Java? PMAX: 2
Jakie są zasady programowania obiektowego? PMAX: 5
Wymień podstawowe typy danych w Java PMAX: 3
```

## 🖥️ Użytkowanie

### Podstawowe kroki:

1. **Uruchom aplikację** - uruchom klasę `Main`
2. **Wczytaj plik** - kliknij "Wczytaj plik z pytaniami" i wybierz plik .txt
3. **Ustaw punktację** - dla każdego pytania ustaw liczbę uzyskanych punktów (0-PMAX)
4. **Wprowadź błędy** - ustaw liczbę błędów do odjęcia (opcjonalne)
5. **Dodaj punkty dodatkowe** - wprowadź dodatkowe punkty (opcjonalne)
6. **Oblicz wynik** - kliknij "Oblicz wynik" aby zobaczyć rezultat

### Panel wyników pokazuje:
- Łączną liczbę punktów
- Maksymalną liczbę punktów
- Procent uzyskanych punktów
- Liczbę błędów

## 🏗️ Struktura projektu

```
src/
├── Main.java                    # Główna klasa aplikacji
├── model/
│   ├── Question.java           # Model pytania
│   ├── Test.java              # Model testu
│   └── TestResult.java        # Model wyniku testu
├── service/
│   ├── FileReader.java        # Wczytywanie plików
│   ├── ScoreCalculator.java   # Obliczanie punktów
│   └── TestProcessor.java     # Przetwarzanie testów
├── ui/
│   ├── ExamPanel.java         # Główny panel aplikacji
│   ├── TestPanel.java         # Panel z pytaniami
│   └── ResultPanel.java       # Panel wyników
└── utils/
    ├── FileUtils.java         # Narzędzia do plików
    └── ValidationUtils.java   # Walidacja danych
```

## 🎨 Interfejs użytkownika

Aplikacja posiada nowoczesny interfejs z:
- **Przejrzystymi kontrolkami** - łatwe w użyciu spinery i przyciski
- **Responsywnym układem** - dostosowuje się do rozmiaru okna
- **Efektami hover** - interaktywne elementy UI
- **Czytelną typografią** - font Segoe UI dla lepszej czytelności

## 🧪 Testowanie

### Przykładowe pliki testowe

Utwórz plik `test.txt` z zawartością:
```
Podstawy programowania w Java PMAX: 5
Zasady dziedziczenia w OOP PMAX: 3
Kolekcje w języku Java PMAX: 4
Obsługa wyjątków PMAX: 2
```

### Testowanie funkcjonalności

1. Wczytaj przykładowy plik
2. Przetestuj różne kombinacje punktów
3. Sprawdź działanie błędów i punktów dodatkowych
4. Zweryfikuj poprawność obliczeń procentowych

## 🤝 Współpraca

Jestem otwarty do współpracy nad tym kodem. Jeżeli chcesz włączyć się w tworzenie tego projektu stwórz pullrequest.


## 📋 Planowane funkcjonalności

- [ ] Zapisywanie i wczytywanie sesji testowych
- [ ] Export wyników do PDF
- [ ] Historia przeprowadzonych testów
- [ ] Statystyki i raporty
- [ ] Obsługa większej liczby formatów plików

## 🐛 Zgłaszanie błędów

Jeśli znajdziesz błąd, proszę napisz do mnie na maila podanego w profilu GitHub z następującymi informacjami:

- Opis błędu i kroków do reprodukcji
- Używany system operacyjny
- Wersja Javy
- Przykładowy plik z pytaniami (jeśli dotyczy)
- Zrzuty ekranu błędu

## 📄 Licencja

Projekt jest dostępny na licencji MIT License. Zobacz plik [LICENSE](LICENSE) po więcej szczegółów.

## 👥 Autorzy

- **SzpakPoland** - *Autor projektu* - [SzpakPoland](https://github.com/SzpakPoland)

## 📞 Kontakt

- GitHub Issues: [Issues](https://github.com/SzpakPoland/PanelEgzaminy/issues)
- GitHub Profile: [@SzpakPoland](https://github.com/SzpakPoland)

---

⭐ **Jeśli projekt Ci się podoba, zostaw gwiazdkę!** ⭐
