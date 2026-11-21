# Aulas objetivas de `java.time`

Guia de sintaxe e métodos para resolver todos os exercícios do arquivo `exercicios-java-time.md`.
Cada aula mostra **o que usar, como escrever e o que evitar**. No final há um mapa dizendo qual aula serve para qual exercício.

> Requisitos: Java 9+ para `toMinutesPart()`, `datesUntil()` e similares. `Locale.of(...)` exige Java 19+; em versões anteriores use `new Locale("pt", "BR")`.

---

## Índice

1. [Visão geral: qual tipo usar](#aula-1--visão-geral-qual-tipo-usar)
2. [`LocalDate`](#aula-2--localdate)
3. [`LocalTime` e `LocalDateTime`](#aula-3--localtime-e-localdatetime)
4. [`DayOfWeek`, `Month` e comparações](#aula-4--dayofweek-month-e-comparações)
5. [`DateTimeFormatter`: formatar](#aula-5--datetimeformatter-formatar-com-format)
6. [`DateTimeFormatter`: parsing e validação](#aula-6--datetimeformatter-parsing-e-validação)
7. [`Duration`](#aula-7--duration)
8. [`Period` e `ChronoUnit`](#aula-8--period-e-chronounit)
9. [`Instant`](#aula-9--instant)
10. [Fusos: `ZoneId`, `ZonedDateTime`, `OffsetDateTime`](#aula-10--fusos-zoneid-zoneddatetime-offsetdatetime)
11. [`TemporalAdjusters` e `YearMonth`](#aula-11--temporaladjusters-e-yearmonth)
12. [`Clock`: tempo controlado](#aula-12--clock-tempo-controlado)
13. [Conversão de código legado](#aula-13--conversão-de-código-legado)
14. [Intervalos e sobreposição](#aula-14--intervalos-e-sobreposição)
15. [Mapa: exercício → aula](#mapa-exercício--aula)
16. [Armadilhas](#armadilhas-resumo)

---

## Aula 1 — Visão geral: qual tipo usar

Todos ficam no pacote `java.time` (e `java.time.format`, `java.time.temporal`).

```java
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
```

| Tipo | Representa | Tem fuso? | Exemplo de uso |
|---|---|---|---|
| `LocalDate` | Só data | Não | Nascimento, vencimento |
| `LocalTime` | Só hora | Não | Horário de abertura |
| `LocalDateTime` | Data + hora | Não | Agendamento, nota fiscal |
| `Instant` | Ponto exato na linha do tempo (UTC) | Sim (sempre UTC) | Log, timestamp, auditoria |
| `ZonedDateTime` | Data + hora + fuso com regras (horário de verão) | Sim | Reunião internacional |
| `OffsetDateTime` | Data + hora + deslocamento fixo (`-03:00`) | Parcial | Resposta de API |
| `Duration` | Tempo em horas/min/seg | n/a | Horas trabalhadas, SLA |
| `Period` | Tempo em anos/meses/dias | n/a | Idade, prazo de contrato |
| `YearMonth` | Mês + ano | n/a | Relatório mensal |
| `Clock` | Fonte de "agora" | n/a | Testes |

**Regras de ouro**

- Todos os objetos são **imutáveis**: `data.plusDays(1)` **devolve um novo objeto**.

```java
LocalDate d = LocalDate.of(2026, 11, 8);
d.plusDays(1);            // resultado descartado, d continua 08/11
d = d.plusDays(1);        // correto
```

- Precisa de **momento exato** (log, auditoria)? Use `Instant`.
- Precisa de **data/hora de calendário** sem fuso (agenda, aniversário)? Use `Local*`.

---

## Aula 2 — `LocalDate`

### Criar

```java
LocalDate hoje   = LocalDate.now();
LocalDate data   = LocalDate.of(2024, 2, 29);          // ano, mês, dia
LocalDate data2  = LocalDate.of(2024, Month.MARCH, 5); // com enum Month
LocalDate iso    = LocalDate.parse("2024-03-16");      // só aceita ISO (yyyy-MM-dd)
```

### Ler partes

```java
data.getYear();         // 2024
data.getMonthValue();   // 2   (int)
data.getMonth();        // Month.FEBRUARY (enum)
data.getDayOfMonth();   // 29
data.getDayOfWeek();    // DayOfWeek.THURSDAY
data.getDayOfYear();    // 60
data.isLeapYear();      // true
data.lengthOfMonth();   // 29
data.lengthOfYear();    // 366
```

### Somar e subtrair

```java
data.plusDays(14);
data.plusWeeks(2);
data.plusMonths(1);
data.plusYears(1);
data.minusDays(7);
data.plus(3, ChronoUnit.DAYS);       // forma genérica
data.plus(Period.ofMonths(2));
```

> `LocalDate.of(2024, 1, 31).plusMonths(1)` resulta em `2024-02-29`. O Java **ajusta** para o último dia válido do mês.

### Trocar partes (`with`)

```java
data.withYear(2025);          // 29/02/2024 vira 28/02/2025 (ajusta sozinho)
data.withMonth(5);
data.withDayOfMonth(1);
data.with(TemporalAdjusters.lastDayOfMonth());
```

### Converter

```java
data.atStartOfDay();            // LocalDateTime 00:00
data.atTime(14, 30);            // LocalDateTime 14:30
data.atTime(LocalTime.NOON);
```

### Gerar sequência de datas

```java
LocalDate ini = LocalDate.of(2026, 11, 1);
LocalDate fim = LocalDate.of(2026, 12, 1);       // fim é EXCLUSIVO
ini.datesUntil(fim).forEach(System.out::println);  // Stream<LocalDate>
```

### Exemplo: idade e próximo aniversário (Exercício 1)

```java
LocalDate nasc = LocalDate.of(1990, 2, 29);
LocalDate hoje = LocalDate.now();

int idade = Period.between(nasc, hoje).getYears();

LocalDate proximo = nasc.withYear(hoje.getYear());
if (proximo.isBefore(hoje)) {
    proximo = proximo.plusYears(1);
}
long diasAte = ChronoUnit.DAYS.between(hoje, proximo);

boolean aniversarioHoje = MonthDay.from(nasc).equals(MonthDay.from(hoje));
```

---

## Aula 3 — `LocalTime` e `LocalDateTime`

### `LocalTime`

```java
LocalTime abre  = LocalTime.of(6, 30);
LocalTime fecha = LocalTime.of(20, 0);
LocalTime t     = LocalTime.parse("08:02");     // formato HH:mm ou HH:mm:ss
LocalTime agora = LocalTime.now();

t.getHour();  t.getMinute();  t.getSecond();
t.plusHours(2);  t.minusMinutes(15);
t.withMinute(0);
LocalTime.MIDNIGHT;  LocalTime.NOON;  LocalTime.MIN;  LocalTime.MAX;
```

### `LocalDateTime`

```java
LocalDateTime ldt  = LocalDateTime.of(2026, 11, 8, 14, 35);          // sem segundos
LocalDateTime ldt2 = LocalDateTime.of(2026, 11, 8, 14, 35, 0);
LocalDateTime ldt3 = LocalDateTime.of(LocalDate.of(2026, 11, 8), LocalTime.of(14, 35));
LocalDateTime ldt4 = LocalDateTime.parse("2026-11-08T14:35:00");     // ISO com 'T'
LocalDateTime agora = LocalDateTime.now();

ldt.toLocalDate();
ldt.toLocalTime();
ldt.plusHours(4);
ldt.minusDays(1);
ldt.withHour(8).withMinute(0).withSecond(0).withNano(0);
ldt.truncatedTo(ChronoUnit.MINUTES);     // zera segundos e nanos
```

### Exemplo: horário de funcionamento (Exercício 3)

```java
boolean estaAberto(LocalDateTime momento) {
    LocalTime abre  = LocalTime.of(6, 30);
    LocalTime fecha = (momento.getDayOfWeek() == DayOfWeek.SUNDAY)
            ? LocalTime.of(12, 0)
            : LocalTime.of(20, 0);

    LocalTime hora = momento.toLocalTime();
    return !hora.isBefore(abre) && hora.isBefore(fecha);   // abre inclusive, fecha exclusivo
}
```

---

## Aula 4 — `DayOfWeek`, `Month` e comparações

### Comparar

```java
a.isBefore(b);     // a < b
a.isAfter(b);      // a > b
a.isEqual(b);      // a == b (LocalDate)
a.equals(b);       // igualdade estrita
a.compareTo(b);    // negativo, 0 ou positivo
```

Funcionam em `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant` e `ZonedDateTime`.
Nunca use `==` para comparar datas.

### Dentro de um intervalo (padrão "início inclusivo, fim exclusivo")

```java
boolean dentro = !x.isBefore(inicio) && x.isBefore(fim);
```

### `DayOfWeek`

```java
DayOfWeek dia = data.getDayOfWeek();
dia == DayOfWeek.SATURDAY;
dia.getValue();            // 1 = segunda ... 7 = domingo
dia.plus(2);               // dois dias depois
dia.getDisplayName(TextStyle.FULL,  Locale.of("pt", "BR"));   // "domingo" (formato varia conforme JDK)
dia.getDisplayName(TextStyle.SHORT, Locale.of("pt", "BR"));   // "dom."
```

### Fim de semana

```java
boolean fimDeSemana(LocalDate d) {
    DayOfWeek w = d.getDayOfWeek();
    return w == DayOfWeek.SATURDAY || w == DayOfWeek.SUNDAY;
}
```

### `Month`

```java
Month.NOVEMBER.getValue();                                         // 11
Month.of(11);
Month.NOVEMBER.getDisplayName(TextStyle.FULL, Locale.of("pt", "BR"));  // "novembro"
```

---

## Aula 5 — `DateTimeFormatter`: formatar com `.format()`

### Sintaxe

```java
DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
String texto = ldt.format(f);        // "08/11/2026 14:35"
// equivalente: f.format(ldt);
```

> Crie o formatter **uma vez** (`static final`). Ele é imutável e thread-safe.

### Tabela de padrões

| Padrão | Significado | Exemplo |
|---|---|---|
| `yyyy` / `uuuu` | Ano (4 dígitos) | `2026` |
| `yy` | Ano (2 dígitos) | `26` |
| `MM` | Mês numérico (**maiúsculo**) | `11` |
| `MMM` | Mês abreviado | `nov.` |
| `MMMM` | Mês por extenso | `novembro` |
| `dd` | Dia com zero à esquerda | `08` |
| `d` | Dia sem zero | `8` |
| `EEE` | Dia da semana abreviado | `dom.` |
| `EEEE` | Dia da semana por extenso | `domingo` |
| `HH` | Hora 0–23 | `14` |
| `hh` | Hora 1–12 | `02` |
| `mm` | Minuto (**minúsculo**) | `35` |
| `ss` | Segundo | `00` |
| `SSS` | Milissegundos | `123` |
| `a` | AM/PM | `PM` |
| `z` | Fuso (sigla) | `BRT` |
| `VV` | Id do fuso | `America/Sao_Paulo` |
| `XXX` | Offset | `-03:00` |
| `'texto'` | Texto literal entre aspas simples | `'de'` |

### Idioma (`Locale`)

```java
Locale ptBR = Locale.of("pt", "BR");          // Java 19+
// Locale ptBR = new Locale("pt", "BR");      // Java 8–18
// Locale ptBR = Locale.forLanguageTag("pt-BR");  // funciona em todas

DateTimeFormatter ext = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", ptBR);
ldt.format(ext);     // "domingo, 8 de novembro de 2026"
```

> Sem o `Locale`, o formatter usa o idioma padrão da JVM. Para texto de dia/mês, sempre informe.

### Formatters prontos

```java
DateTimeFormatter.ISO_LOCAL_DATE;        // 2026-11-08
DateTimeFormatter.ISO_LOCAL_DATE_TIME;   // 2026-11-08T14:35:00
DateTimeFormatter.ISO_INSTANT;           // 2026-11-08T17:35:00Z
DateTimeFormatter.BASIC_ISO_DATE;        // 20261108
```

### Estilos localizados

```java
DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(ptBR);
```

### Exemplo: três saídas da nota fiscal (Exercício 4)

```java
LocalDateTime ldt = LocalDateTime.of(2026, 11, 8, 14, 35, 0);

String tela    = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
String arquivo = ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
String cabecalho = ldt.format(
        DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", ptBR));
```

### Formatar `Instant` (exige fuso)

```java
// ERRADO: lança UnsupportedTemporalTypeException
// DateTimeFormatter.ofPattern("dd/MM/yyyy").format(Instant.now());

DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z")
        .withZone(ZoneId.of("America/Sao_Paulo"));
f2.format(Instant.now());
```

---

## Aula 6 — `DateTimeFormatter`: parsing e validação

### Parsing básico

```java
DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate d = LocalDate.parse("15/03/2024", f);

LocalDateTime dt = LocalDateTime.parse("15/03/2024 08:30",
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
```

> O padrão precisa casar **exatamente** com o texto. `"5/3/2024"` não casa com `dd/MM/yyyy`; use `d/M/yyyy`.

### Tratar erro

```java
try {
    LocalDate d = LocalDate.parse("31/02/2024", f);
} catch (DateTimeParseException e) {
    System.out.println("Data inválida: " + e.getParsedString());
}
```

### `ResolverStyle`: rigor da validação

| Estilo | `31/02/2024` | Comportamento |
|---|---|---|
| `STRICT` | Erro | Rejeita datas inexistentes |
| `SMART` (padrão) | Ajusta para `29/02/2024` ou erro, conforme o campo | Ajusta dias até o limite do mês |
| `LENIENT` | `02/03/2024` | Transborda para o mês seguinte |

```java
DateTimeFormatter estrito = DateTimeFormatter
        .ofPattern("dd/MM/uuuu")                 // uuuu, não yyyy
        .withResolverStyle(ResolverStyle.STRICT);

LocalDate.parse("29/02/2023", estrito);          // lança DateTimeParseException
```

> No modo `STRICT`, `yyyy` (ano da era) exige também a era e costuma falhar. **Use `uuuu`.**

### Vários formatos (Exercício 5)

```java
private static final List<DateTimeFormatter> FORMATOS = List.of(
        DateTimeFormatter.ofPattern("dd/MM/uuuu"),
        DateTimeFormatter.ISO_LOCAL_DATE,                       // 2024-03-16
        DateTimeFormatter.ofPattern("dd-MM-uu"),                // 17-03-24
        DateTimeFormatter.ofPattern("d 'de' MMMM 'de' uuuu", Locale.forLanguageTag("pt-BR"))
);

LocalDate parseFlexivel(String texto) {
    for (DateTimeFormatter f : FORMATOS) {
        try {
            return LocalDate.parse(texto.trim(), f);
        } catch (DateTimeParseException ignorado) {
            // tenta o próximo
        }
    }
    throw new IllegalArgumentException("Formato de data não reconhecido: " + texto);
}
```

### Alternativa: `DateTimeFormatterBuilder` com partes opcionais

```java
DateTimeFormatter flex = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendOptional(DateTimeFormatter.ofPattern("dd/MM/uuuu"))
        .appendOptional(DateTimeFormatter.ofPattern("uuuu-MM-dd"))
        .appendOptional(DateTimeFormatter.ofPattern("dd-MM-uu"))
        .toFormatter(Locale.forLanguageTag("pt-BR"));
```

### Mês/ano (`YearMonth`)

```java
YearMonth ym = YearMonth.parse("11/2026", DateTimeFormatter.ofPattern("MM/yyyy"));
```

---

## Aula 7 — `Duration`

Mede tempo em **horas, minutos, segundos e nanos**. Funciona com `LocalTime`, `LocalDateTime` e `Instant` (não com `LocalDate`).

### Criar

```java
Duration d1 = Duration.between(inicio, fim);     // fim - inicio
Duration d2 = Duration.ofHours(8);
Duration d3 = Duration.ofMinutes(45);
Duration d4 = Duration.ofSeconds(30);
Duration d5 = Duration.ofDays(1);                // 24h exatas
Duration d6 = Duration.parse("PT1H30M");         // ISO-8601
Duration zero = Duration.ZERO;
```

### Operar

```java
d1.plus(d2);
d1.minus(d2);
d1.plusMinutes(10);
d1.multipliedBy(2);
d1.dividedBy(2);
d1.negated();
d1.abs();
d1.isNegative();
d1.isZero();
d1.compareTo(d2);       // negativo, 0 ou positivo
```

### Extrair valores

| Método | Retorna |
|---|---|
| `toDays()` | Total de dias |
| `toHours()` | **Total** de horas (ex.: 51) |
| `toMinutes()` | **Total** de minutos |
| `toSeconds()` | **Total** de segundos |
| `toMillis()` | **Total** de milissegundos |
| `toDaysPart()` | Parte de dias |
| `toHoursPart()` | Parte de horas (0–23) |
| `toMinutesPart()` | Parte de minutos (0–59) |
| `toSecondsPart()` | Parte de segundos (0–59) |

> `toXxx()` = total. `toXxxPart()` = pedaço para exibir. Para 51h30min: `toHours()` = 51, `toHoursPart()` = 3, `toDaysPart()` = 2.

### Exibir como `HH:mm` (Exercício 7)

```java
Duration total = Duration.between(LocalTime.of(8, 2),  LocalTime.of(12, 10))
        .plus(Duration.between(LocalTime.of(13, 5), LocalTime.of(17, 48)));

String texto = String.format("%02d:%02d", total.toHours(), total.toMinutesPart());   // 08:51

Duration saldo = total.minus(Duration.ofHours(8));
if (saldo.isNegative()) {
    System.out.println("Saldo negativo de " + saldo.abs().toMinutes() + " min");
} else {
    System.out.println("Hora extra de " + saldo.toMinutes() + " min");
}
```

### Texto legível: `2 dias, 3 horas e 15 minutos` (Exercício 8)

```java
String legivel(Duration d) {
    List<String> partes = new ArrayList<>();
    if (d.toDaysPart() > 0)    partes.add(d.toDaysPart() + (d.toDaysPart() == 1 ? " dia" : " dias"));
    if (d.toHoursPart() > 0)   partes.add(d.toHoursPart() + (d.toHoursPart() == 1 ? " hora" : " horas"));
    if (d.toMinutesPart() > 0) partes.add(d.toMinutesPart() + (d.toMinutesPart() == 1 ? " minuto" : " minutos"));

    if (partes.isEmpty()) return "menos de 1 minuto";
    if (partes.size() == 1) return partes.get(0);
    return String.join(", ", partes.subList(0, partes.size() - 1))
            + " e " + partes.get(partes.size() - 1);
}
```

### SLA

```java
Duration resolucao = Duration.between(abertoEm, resolvidoEm);
boolean cumpriu = resolucao.compareTo(Duration.ofHours(4)) <= 0;
```

---

## Aula 8 — `Period` e `ChronoUnit`

### `Period`: diferença em calendário (anos, meses, dias)

Funciona com `LocalDate`.

```java
Period p = Period.between(dataInicial, dataFinal);   // final - inicial
p.getYears();
p.getMonths();
p.getDays();
p.isNegative();
p.toTotalMonths();

Period.ofDays(10);
Period.ofMonths(30);
Period.of(1, 2, 3);          // 1 ano, 2 meses, 3 dias
data.plus(Period.ofMonths(30));
```

> `Period` guarda anos, meses e dias **separados**. `getDays()` é só a parte de dias, **não** o total.

### `ChronoUnit`: total em uma única unidade

```java
ChronoUnit.DAYS.between(a, b);       // long
ChronoUnit.WEEKS.between(a, b);
ChronoUnit.MONTHS.between(a, b);
ChronoUnit.YEARS.between(a, b);
ChronoUnit.HOURS.between(dt1, dt2);  // com LocalDateTime
ChronoUnit.MINUTES.between(dt1, dt2);
```

A ordem importa: `between(inicio, fim)`. Se `fim` vier antes de `inicio`, o resultado é negativo.

### Exemplo: contrato de aluguel (Exercício 9)

```java
LocalDate inicio = LocalDate.of(2024, 1, 15);
LocalDate termino = inicio.plusMonths(30);              // 15/07/2026
LocalDate hoje = LocalDate.now();

Period restante = Period.between(hoje, termino);        // anos, meses e dias
long diasRestantes = ChronoUnit.DAYS.between(hoje, termino);

if (restante.isNegative()) {
    System.out.println("Contrato encerrado");
} else {
    System.out.printf("Faltam %d anos, %d meses e %d dias (%d dias no total)%n",
            restante.getYears(), restante.getMonths(), restante.getDays(), diasRestantes);
}
```

### `Period` × `Duration`

| | `Period` | `Duration` |
|---|---|---|
| Unidades | Anos, meses, dias | Dias fixos (24h), horas, min, seg |
| Depende do calendário? | Sim (mês tem 28–31 dias) | Não (tempo físico) |
| Funciona com | `LocalDate` | `LocalTime`, `LocalDateTime`, `Instant` |
| `plus(1 mês)` | Possível | Não existe "mês" |

### Multa por atraso (Exercício 2)

```java
LocalDate emprestimo = LocalDate.of(2026, 10, 20);
LocalDate limite = emprestimo.plusDays(14);
LocalDate hoje = LocalDate.now();

boolean atrasado = hoje.isAfter(limite);
long diasAtraso = atrasado ? ChronoUnit.DAYS.between(limite, hoje) : 0;
BigDecimal multa = new BigDecimal("1.50").multiply(BigDecimal.valueOf(diasAtraso));
```

---

## Aula 9 — `Instant`

Ponto exato na linha do tempo, sempre em **UTC**, sem calendário nem fuso. Ideal para log, auditoria e timestamp.

### Criar

```java
Instant agora = Instant.now();
Instant i1 = Instant.parse("2024-05-10T13:45:30Z");        // precisa terminar em Z
Instant i2 = Instant.ofEpochSecond(1_700_000_000L);
Instant i3 = Instant.ofEpochMilli(System.currentTimeMillis());
```

### Operar

```java
agora.plus(Duration.ofMinutes(30));
agora.minusSeconds(10);
agora.plus(1, ChronoUnit.DAYS);            // dias são aceitos (24h); meses e anos não
agora.isBefore(outro);
agora.isAfter(outro);
agora.toEpochMilli();
agora.getEpochSecond();
agora.truncatedTo(ChronoUnit.SECONDS);
```

### Medir tempo de execução (Exercício 13)

```java
Instant ini = Instant.now();
executarOperacao();
Duration gasto = Duration.between(ini, Instant.now());
System.out.println(gasto.toMillis() + " ms");
```

> Para medir performance com precisão, `System.nanoTime()` é mais adequado. `Instant.now()` serve para o exercício e para registrar quando algo aconteceu.

### Cache com expiração (Exercício 13)

```java
class CacheSimples<T> {
    private final Duration validade;
    private T valor;
    private Instant criadoEm;

    CacheSimples(Duration validade) { this.validade = validade; }

    void guardar(T novo) {
        this.valor = novo;
        this.criadoEm = Instant.now();
    }

    Optional<T> obter() {
        if (valor == null) return Optional.empty();
        boolean expirou = Duration.between(criadoEm, Instant.now()).compareTo(validade) > 0;
        return expirou ? Optional.empty() : Optional.of(valor);
    }
}

// uso: new CacheSimples<String>(Duration.ofSeconds(30));
```

---

## Aula 10 — Fusos: `ZoneId`, `ZonedDateTime`, `OffsetDateTime`

### `ZoneId`

```java
ZoneId sp     = ZoneId.of("America/Sao_Paulo");
ZoneId lisboa = ZoneId.of("Europe/Lisbon");
ZoneId toquio = ZoneId.of("Asia/Tokyo");
ZoneId ny     = ZoneId.of("America/New_York");
ZoneId londres = ZoneId.of("Europe/London");
ZoneId local  = ZoneId.systemDefault();
```

> Use sempre o id **região/cidade**. Siglas como `BRT` ou `EST` são ambíguas e não carregam regras de horário de verão.

### Instant → data/hora em um fuso

```java
Instant evento = Instant.parse("2026-11-08T17:35:00Z");

ZonedDateTime emSp = evento.atZone(sp);

DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");
System.out.println(emSp.format(f));
System.out.println(evento.atZone(lisboa).format(f));
System.out.println(evento.atZone(toquio).format(f));
```

### `LocalDateTime` → `ZonedDateTime`

```java
LocalDateTime ldt = LocalDateTime.of(2025, 3, 20, 15, 0);
ZonedDateTime reuniaoNy = ZonedDateTime.of(ldt, ny);     // ou ldt.atZone(ny)
```

### `withZoneSameInstant` × `withZoneSameLocal`

| Método | O que faz | Quando usar |
|---|---|---|
| `withZoneSameInstant(zone)` | Mantém o **momento** e muda a hora exibida | **Converter horário entre cidades** |
| `withZoneSameLocal(zone)` | Mantém a **hora do relógio** e muda o momento | Reinterpretar o mesmo texto em outro fuso (quase sempre é erro) |

```java
ZonedDateTime bh      = reuniaoNy.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
ZonedDateTime londres = reuniaoNy.withZoneSameInstant(ZoneId.of("Europe/London"));
// 20/03/2025 15:00 em NY (EDT, UTC-4) = 16:00 em BH (UTC-3) = 19:00 em Londres (UTC+0)
```

> Em março, EUA e Europa mudam o horário de verão em semanas diferentes. Teste datas de 9 a 30 de março para ver a diferença entre fusos mudar.

### `ZonedDateTime` → outros tipos

```java
zdt.toInstant();
zdt.toLocalDateTime();
zdt.toLocalDate();
zdt.toOffsetDateTime();
zdt.getZone();
zdt.getOffset();
```

### `OffsetDateTime`

```java
OffsetDateTime a = OffsetDateTime.parse("2024-05-10T10:45:30-03:00");
Instant b = Instant.parse("2024-05-10T13:45:30Z");

a.toInstant();                               // 2024-05-10T13:45:30Z
a.toInstant().equals(b);                     // true: mesmo instante
a.toInstant().compareTo(b) == 0;             // true

LocalDateTime emSp = b.atZone(sp).toLocalDateTime();   // 2024-05-10T10:45:30
```

> `OffsetDateTime.equals` compara também o offset; para saber se é o mesmo **momento**, compare `toInstant()` ou use `isEqual`.

### Exercício 12 completo

```java
Instant i = Instant.parse("2024-05-10T13:45:30Z");
OffsetDateTime o = OffsetDateTime.parse("2024-05-10T10:45:30-03:00");

boolean mesmoInstante = i.equals(o.toInstant());
LocalDateTime sp1 = i.atZone(sp).toLocalDateTime();
LocalDateTime sp2 = o.atZoneSameInstant(sp).toLocalDateTime();
```

---

## Aula 11 — `TemporalAdjusters` e `YearMonth`

### `TemporalAdjusters`

Usados com `data.with(...)`.

```java
import static java.time.temporal.TemporalAdjusters.*;

data.with(firstDayOfMonth());
data.with(lastDayOfMonth());
data.with(firstDayOfNextMonth());
data.with(next(DayOfWeek.MONDAY));          // próxima segunda (nunca hoje)
data.with(nextOrSame(DayOfWeek.MONDAY));    // hoje se já for segunda
data.with(previous(DayOfWeek.FRIDAY));
data.with(previousOrSame(DayOfWeek.FRIDAY));
data.with(firstInMonth(DayOfWeek.MONDAY));  // primeira segunda do mês
data.with(lastInMonth(DayOfWeek.FRIDAY));
data.with(dayOfWeekInMonth(2, DayOfWeek.TUESDAY));  // segunda terça do mês
```

### Boleto: empurrar para dia útil (Exercício 14)

```java
LocalDate ajustarVencimento(LocalDate venc, Set<LocalDate> feriados) {
    while (fimDeSemana(venc) || feriados.contains(venc)) {
        venc = venc.plusDays(1);
    }
    return venc;
}
```

Versão curta só com fim de semana:

```java
LocalDate v = venc.getDayOfWeek().getValue() >= 6
        ? venc.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        : venc;
```

### `YearMonth`

```java
YearMonth ym  = YearMonth.of(2026, 11);
YearMonth ym2 = YearMonth.parse("2026-11");                  // ISO
YearMonth ym3 = YearMonth.parse("11/2026", DateTimeFormatter.ofPattern("MM/yyyy"));
YearMonth atual = YearMonth.now();
YearMonth deData = YearMonth.from(LocalDate.now());

ym.atDay(1);             // primeiro dia
ym.atEndOfMonth();       // último dia
ym.lengthOfMonth();      // 30
ym.plusMonths(1);
ym.getYear();  ym.getMonthValue();
ym.isLeapYear();
```

### 5º dia útil do mês (Exercício 15)

```java
LocalDate quintoDiaUtil(YearMonth ym) {
    LocalDate d = ym.atDay(1);
    int uteis = 0;
    while (true) {
        if (!fimDeSemana(d)) {
            uteis++;
            if (uteis == 5) return d;
        }
        d = d.plusDays(1);
    }
}

List<LocalDate> pagamentosDoAno(int ano) {
    return IntStream.rangeClosed(1, 12)
            .mapToObj(m -> quintoDiaUtil(YearMonth.of(ano, m)))
            .toList();
}
```

> Para considerar feriados, adicione `&& !feriados.contains(d)` na condição.

### Relatório mensal (Exercício 16)

```java
YearMonth ym = YearMonth.parse("11/2026", DateTimeFormatter.ofPattern("MM/yyyy"));
LocalDate primeiro = ym.atDay(1);
LocalDate ultimo = ym.atEndOfMonth();

long diasUteis = primeiro.datesUntil(ultimo.plusDays(1))     // fim exclusivo
        .filter(d -> !fimDeSemana(d))
        .count();

List<LocalDate> sabados = primeiro.datesUntil(ultimo.plusDays(1))
        .filter(d -> d.getDayOfWeek() == DayOfWeek.SATURDAY)
        .toList();
```

---

## Aula 12 — `Clock`: tempo controlado

`Clock` é a fonte de "agora". Em produção usa o relógio do sistema; em testes você fixa o tempo.

```java
Clock sistema = Clock.systemDefaultZone();
Clock utc     = Clock.systemUTC();
Clock fixo    = Clock.fixed(Instant.parse("2026-11-08T12:00:00Z"), ZoneId.of("America/Sao_Paulo"));
Clock adiantado = Clock.offset(sistema, Duration.ofDays(3));
```

Todos os `now()` aceitam um `Clock`:

```java
LocalDate.now(clock);
LocalDateTime.now(clock);
LocalTime.now(clock);
Instant.now(clock);
ZonedDateTime.now(clock);
```

### Refatoração (Exercício 18)

```java
class Biblioteca {
    private final Clock clock;

    Biblioteca(Clock clock) { this.clock = clock; }

    boolean atrasado(LocalDate emprestimo) {
        LocalDate hoje = LocalDate.now(clock);
        return hoje.isAfter(emprestimo.plusDays(14));
    }
}
```

### Teste com JUnit 5

```java
@Test
void devolucaoNoPrazoNaoEstaAtrasada() {
    Clock fixo = Clock.fixed(Instant.parse("2026-11-10T12:00:00Z"), ZoneId.of("UTC"));
    Biblioteca b = new Biblioteca(fixo);

    assertFalse(b.atrasado(LocalDate.of(2026, 11, 1)));   // limite dia 15
}

@Test
void devolucaoForaDoPrazoEstaAtrasada() {
    Clock fixo = Clock.fixed(Instant.parse("2026-11-20T12:00:00Z"), ZoneId.of("UTC"));
    Biblioteca b = new Biblioteca(fixo);

    assertTrue(b.atrasado(LocalDate.of(2026, 11, 1)));
}
```

> Regra: **nunca** chame `LocalDate.now()` sem argumento dentro de regra de negócio que você precisa testar. Injete o `Clock`.

---

## Aula 13 — Conversão de código legado

### Tabela de conversões

| De | Para | Código |
|---|---|---|
| `java.util.Date` | `Instant` | `date.toInstant()` |
| `Instant` | `java.util.Date` | `Date.from(instant)` |
| `java.util.Date` | `LocalDateTime` | `LocalDateTime.ofInstant(date.toInstant(), zone)` |
| `java.util.Date` | `LocalDate` | `date.toInstant().atZone(zone).toLocalDate()` |
| `LocalDateTime` | `java.util.Date` | `Date.from(ldt.atZone(zone).toInstant())` |
| `Timestamp` | `LocalDateTime` | `ts.toLocalDateTime()` |
| `LocalDateTime` | `Timestamp` | `Timestamp.valueOf(ldt)` |
| `Timestamp` | `Instant` | `ts.toInstant()` |
| `Instant` | `Timestamp` | `Timestamp.from(instant)` |
| `java.sql.Date` | `LocalDate` | `sqlDate.toLocalDate()` |
| `LocalDate` | `java.sql.Date` | `java.sql.Date.valueOf(localDate)` |

### Exemplo (Exercício 17)

```java
ZoneId zone = ZoneId.systemDefault();

java.util.Date legado = new java.util.Date();
LocalDateTime ldt = LocalDateTime.ofInstant(legado.toInstant(), zone);
LocalDateTime amanha = ldt.plusDays(1);
java.util.Date volta = java.util.Date.from(amanha.atZone(zone).toInstant());

java.sql.Timestamp ts = java.sql.Timestamp.valueOf(LocalDateTime.now());
LocalDateTime deTs = ts.toLocalDateTime();

java.sql.Date sd = java.sql.Date.valueOf(LocalDate.of(2026, 11, 8));
LocalDate ld = sd.toLocalDate();
```

> **Cuidado:** `java.sql.Date.toInstant()` lança `UnsupportedOperationException`. Use `toLocalDate()`.
> Ao converter entre `Date` e `Local*`, o fuso entra na conta. Seja explícito com `ZoneId`.

---

## Aula 14 — Intervalos e sobreposição

### Regra de sobreposição

Dois intervalos `[inicioA, fimA)` e `[inicioB, fimB)` se sobrepõem quando:

```java
boolean sobrepoe = inicioA.isBefore(fimB) && inicioB.isBefore(fimA);
```

Consultas coladas (uma termina 09:30 e outra começa 09:30) **não** se sobrepõem.

### Classe de consulta (Desafio final)

```java
record Consulta(LocalDateTime inicio, Duration duracao, Instant criadaEm) {
    LocalDateTime fim() { return inicio.plus(duracao); }

    boolean conflitaCom(Consulta outra) {
        return this.inicio.isBefore(outra.fim()) && outra.inicio.isBefore(this.fim());
    }
}
```

### Validar horário de atendimento

```java
static final LocalTime ABRE = LocalTime.of(8, 0);
static final LocalTime FECHA = LocalTime.of(18, 0);
static final LocalTime ALMOCO_INI = LocalTime.of(12, 0);
static final LocalTime ALMOCO_FIM = LocalTime.of(13, 0);

boolean dentroDoExpediente(LocalDateTime ini, Duration dur) {
    if (fimDeSemana(ini.toLocalDate())) return false;

    LocalDateTime fim = ini.plus(dur);
    if (!fim.toLocalDate().equals(ini.toLocalDate())) return false;   // não pode virar o dia

    LocalTime i = ini.toLocalTime();
    LocalTime f = fim.toLocalTime();

    boolean noExpediente = !i.isBefore(ABRE) && !f.isAfter(FECHA);
    boolean batenoAlmoco = i.isBefore(ALMOCO_FIM) && ALMOCO_INI.isBefore(f);

    return noExpediente && !batenoAlmoco;
}
```

### Bloquear passado e feriados

```java
boolean noPassado = inicio.isBefore(LocalDateTime.now(clock));
boolean feriado   = feriados.contains(inicio.toLocalDate());
```

### Listar agenda formatada (`Seg, 10/11 • 09:00–09:30`)

```java
Locale ptBR = Locale.forLanguageTag("pt-BR");
DateTimeFormatter fData = DateTimeFormatter.ofPattern("EEE, dd/MM", ptBR);
DateTimeFormatter fHora = DateTimeFormatter.ofPattern("HH:mm");

String linha(Consulta c) {
    String data = c.inicio().format(fData);
    data = Character.toUpperCase(data.charAt(0)) + data.substring(1);   // "seg." → "Seg."
    return data + " • " + c.inicio().format(fHora) + "–" + c.fim().format(fHora);
}
```

> O Java pode devolver `"seg."` com ponto no pt-BR, dependendo do JDK. Se quiser `Seg` sem ponto, use `.replace(".", "")`.

### Mostrar `criadaEm` no fuso do usuário

```java
ZoneId fusoUsuario = ZoneId.of("America/Sao_Paulo");
String criada = c.criadaEm().atZone(fusoUsuario)
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
```

### Ordenar a agenda

```java
agenda.stream()
      .sorted(Comparator.comparing(Consulta::inicio))
      .map(this::linha)
      .forEach(System.out::println);
```

---

## Mapa: exercício → aula

| Exercício | Tema | Aulas |
|---|---|---|
| 1 | Idade e aniversário | 2, 8 |
| 2 | Devolução de livros | 2, 4, 8 |
| 3 | Horário de funcionamento | 3, 4 |
| 4 | Nota fiscal | 5 |
| 5 | CSV com datas misturadas | 6 |
| 6 | Validação de formulário | 6 |
| 7 | Ponto eletrônico | 7 |
| 8 | SLA de chamados | 7 |
| 9 | Contrato de aluguel | 2, 8 |
| 10 | Log em vários fusos | 5, 9, 10 |
| 11 | Reunião internacional | 10 |
| 12 | Timestamp de API | 9, 10 |
| 13 | Tempo de execução e cache | 7, 9 |
| 14 | Vencimento de boleto | 4, 11 |
| 15 | 5º dia útil | 2, 4, 11 |
| 16 | Relatório mensal | 2, 6, 11 |
| 17 | Código legado | 13 |
| 18 | Testes com `Clock` | 12 |
| Desafio final | Agendamento | 3, 4, 5, 7, 9, 12, 14 |

---

## Armadilhas (resumo)

| Armadilha | Correto |
|---|---|
| `date.plusDays(1);` sem atribuir | `date = date.plusDays(1);` |
| `mm` para mês | `MM` = mês, `mm` = minuto |
| `hh` para hora 24h | `HH` = 0–23, `hh` = 1–12 |
| `YYYY` | `yyyy` (`YYYY` é ano da semana e erra na virada do ano) |
| `yyyy` com `STRICT` | `uuuu` |
| Formatar `Instant` sem fuso | `.withZone(zone)` ou `atZone(zone)` |
| `Duration.between(LocalDate, LocalDate)` | Use `ChronoUnit.DAYS` ou `Period` |
| `period.getDays()` como total de dias | `ChronoUnit.DAYS.between(a, b)` |
| `duration.toHours()` para exibir hora do dia | `toHoursPart()` |
| `withZoneSameLocal` para converter fuso | `withZoneSameInstant` |
| `LocalDateTime` para representar momento absoluto | `Instant` ou `ZonedDateTime` |
| Sigla de fuso (`BRT`, `EST`) | `ZoneId.of("America/Sao_Paulo")` |
| `LocalDate.now()` espalhado pelo código | Injetar `Clock` |
| `==` para comparar datas | `isEqual` ou `equals` |
| `sqlDate.toInstant()` | `sqlDate.toLocalDate()` |
| Esquecer o `Locale` em `EEEE`/`MMMM` | Passar `Locale` no `ofPattern` |
