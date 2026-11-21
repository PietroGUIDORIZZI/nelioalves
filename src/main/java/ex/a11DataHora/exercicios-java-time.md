# Exercícios de Data e Hora em Java (`java.time`)

Cada exercício tem um contexto e um objetivo. Sugiro resolver em um `main` simples e testar com datas diferentes, incluindo casos de borda (ano bissexto, virada de mês, fim de semana).

---

## Nível 1: Fundamentos (`LocalDate`, `LocalTime`, `LocalDateTime`)

**1. Cadastro de clientes**
Uma loja guarda a data de nascimento de cada cliente como `LocalDate`. Crie um método que receba a data de nascimento e retorne:
- a idade em anos completos;
- quantos dias faltam para o próximo aniversário;
- se o cliente faz aniversário hoje.

*Dica:* `Period.between`, `ChronoUnit.DAYS.between`, `withYear`, `isBefore`. Teste com quem nasceu em 29/02.

**2. Biblioteca: devolução de livros**
Um aluno pegou um livro emprestado em `LocalDate`. O prazo é de 14 dias corridos. Calcule:
- a data limite de devolução;
- se ele está atrasado hoje;
- o valor da multa (R$ 1,50 por dia de atraso).

**3. Horário de funcionamento**
Uma padaria abre às 06:30 e fecha às 20:00 (`LocalTime`). Crie um método `estaAberto(LocalDateTime momento)` que também considere que **aos domingos fecha às 12:00**.

*Dica:* `getDayOfWeek()`, `isAfter`, `isBefore`.

---

## Nível 2: Formatação e parsing (`DateTimeFormatter.ofPattern`, `.format()`)

**4. Nota fiscal**
Dado um `LocalDateTime`, gere estas três saídas:
- `08/11/2026 14:35` (para a tela)
- `20261108_143500` (para o nome do arquivo de backup)
- `domingo, 8 de novembro de 2026` (para o cabeçalho da NF, em português)

*Dica:* use `Locale.of("pt", "BR")` ou `new Locale("pt", "BR")` e os padrões `dd`, `MM`, `yyyy`, `HH`, `mm`, `ss`, `EEEE`, `MMMM`.

**5. Importação de CSV sujo**
Você recebeu um CSV com datas em formatos misturados:
```
15/03/2024
2024-03-16
17-03-24
18 de março de 2024
```
Escreva um método `parseFlexivel(String texto)` que tente vários `DateTimeFormatter` e retorne um `LocalDate`. Se nenhum funcionar, lance uma exceção com mensagem clara.

*Dica:* capture `DateTimeParseException`. Tente também `DateTimeFormatterBuilder` com `appendOptional`.

**6. Validação de formulário**
Um campo de texto aceita data de agendamento no formato `dd/MM/yyyy`. Teste a entrada `31/02/2024` e `29/02/2023`. Descubra a diferença entre `ResolverStyle.STRICT`, `SMART` e `LENIENT` e mostre o resultado de cada um.

*Dica:* com `ofPattern`, use `yyyy` em vez de `uuuu` no modo `STRICT` e veja o que acontece.

---

## Nível 3: Intervalos (`Duration`, `Period`, `ChronoUnit`)

**7. Ponto eletrônico**
Um funcionário bateu ponto assim:
- entrada: 08:02
- saída para almoço: 12:10
- volta: 13:05
- saída: 17:48

Calcule o total trabalhado com `Duration` e exiba como `HH:mm`. Depois compare com a jornada de 8h e informe se há horas extras ou saldo negativo.

*Dica:* `Duration.between`, `plus`, `minus`, `toHours`, `toMinutesPart`.

**8. Tempo de uma tarefa em fila**
Um sistema de chamados registra `abertoEm` e `resolvidoEm` (`LocalDateTime`). O SLA é de 4 horas. Escreva:
- um método que retorne a `Duration` de resolução;
- um método que diga se o SLA foi cumprido;
- uma formatação legível, como `2 dias, 3 horas e 15 minutos` (sem biblioteca externa).

**9. Contrato de aluguel**
Um contrato começa em `15/01/2024` e dura 30 meses. Calcule a data de término (`plusMonths`) e o tempo restante como `Period` (anos, meses, dias) a partir de hoje. Em seguida, calcule o total em dias com `ChronoUnit.DAYS`. Por que `Period` e `Duration` dão resultados conceitualmente diferentes?

---

## Nível 4: Instantes e fusos (`Instant`, `ZonedDateTime`, `ZoneId`, `OffsetDateTime`)

**10. Log de servidor**
Seu servidor grava eventos como `Instant` (UTC). Crie um método que receba um `Instant` e exiba o horário para:
- São Paulo (`America/Sao_Paulo`)
- Lisboa (`Europe/Lisbon`)
- Tóquio (`Asia/Tokyo`)

Formate cada um como `dd/MM/yyyy HH:mm z`.

**11. Reunião internacional**
Uma reunião será em `20/03/2025 às 15:00` no horário de Nova York (`America/New_York`). Que horas será em Belo Horizonte e em Londres? Teste também com uma data próxima à mudança de horário de verão dos EUA e veja o que muda.

*Dica:* `ZonedDateTime.of`, `withZoneSameInstant` (diferente de `withZoneSameLocal`).

**12. Timestamp de API**
Uma API REST retorna `"2024-05-10T13:45:30Z"` e outra `"2024-05-10T10:45:30-03:00"`.
- Faça o parse da primeira com `Instant.parse` e da segunda com `OffsetDateTime.parse`.
- Prove que representam o **mesmo instante**.
- Converta ambas para `LocalDateTime` em São Paulo.

**13. Tempo de execução e cache**
Meça quanto tempo um método demora usando `Instant.now()` antes e depois, com `Duration.between`. Depois implemente um cache simples que guarda um valor e o expira após 30 segundos, comparando `Instant.now()` com o instante de criação.

---

## Nível 5: Ajustes e cenários reais

**14. Vencimento de boleto**
Dado um boleto que vence numa data qualquer, se cair em sábado ou domingo, mova para a próxima segunda-feira. Depois incremente o desafio com uma lista de feriados (`Set<LocalDate>`) que também empurre o vencimento.

*Dica:* `TemporalAdjusters.next(DayOfWeek.MONDAY)`, `getDayOfWeek()`.

**15. Calendário de pagamentos**
O pagamento é todo **5º dia útil** do mês. Dado um `YearMonth`, calcule a data. Em seguida, liste os 12 pagamentos do ano.

*Dica:* `YearMonth.atDay`, `atEndOfMonth`, `TemporalAdjusters.firstDayOfMonth`, `lastDayOfMonth`.

**16. Relatório mensal**
Dado um mês/ano informado pelo usuário como `"11/2026"`, gere:
- o primeiro e o último dia do mês;
- quantos dias úteis (seg a sex) ele tem;
- uma lista com todos os sábados.

*Dica:* `YearMonth.parse` com `DateTimeFormatter.ofPattern("MM/yyyy")`, `datesUntil`.

**17. Compatibilidade com código legado**
Seu banco devolve `java.sql.Timestamp` e `java.util.Date`. Converta para `LocalDateTime` e `Instant`, processe, e converta de volta. Teste também `LocalDate` ↔ `java.sql.Date`.

*Dica:* `toInstant()`, `Date.from`, `Timestamp.valueOf`, `toLocalDateTime`.

**18. Testes com tempo controlado (`Clock`)**
Refatore o exercício 2 (biblioteca) para receber um `Clock` em vez de chamar `LocalDate.now()` diretamente. Depois escreva testes com `Clock.fixed(...)` simulando "hoje" em diferentes datas.

---

## Desafio final: mini sistema de agendamento de consultas

Combine tudo:
- Consultas têm `LocalDateTime` de início e `Duration` de duração.
- Atendimento de segunda a sexta, das 08:00 às 18:00, com intervalo de almoço das 12:00 às 13:00.
- Impeça agendamentos que se sobreponham (compare intervalos).
- Impeça agendamentos no passado e em feriados.
- Liste a agenda do dia formatada em português, como `Seg, 10/11 • 09:00–09:30`.
- Guarde o registro de criação como `Instant` e exiba no fuso do usuário.

---

## Armadilhas para observar

- `yyyy` vs `YYYY` (semana-ano pode dar resultado errado na virada do ano)
- `MM` (mês) vs `mm` (minuto) e `HH` (24h) vs `hh` (12h)
- `LocalDateTime` não tem fuso: não use para representar um instante absoluto
- `Duration` mede tempo em horas/minutos/segundos; `Period` mede em dias/meses/anos de calendário
- Objetos de `java.time` são imutáveis: `date.plusDays(1)` não altera `date`
