# Test

## Testing Checklists

### S1 Release checklist
* [x] C1 Reports
    - Gerar relatórios desempenho -> passed @LEI-122663
    - Validar integridade dados -> passed @LEI-122663
    - Confirmar diretoria correta -> passed @LEI-122663

### S2 Automated tests checklist
* [x] C2 Unit Tests
    - Executar testes unitários -> passed @LEI-122663
    - Verificar anotações Allure -> passed @LEI-122663
    - Validar fluxos principais -> passed @LEI-122663

## Unit tests

### S3 Ship test cases
* [x] C3 BargeTest
  tags: unit-tests, test-cases
    - Criar objeto Barge -> passed @LEI-122663
    - Validar capacidade carga -> passed @LEI-122663
    - Testar limite excedido -> passed @LEI-122663

* [x] C4 CaravelTest
  tags: unit-tests, test-cases
    - Instanciar Caravel -> passed @LEI-122663
    - Simular navegação adversa -> passed @LEI-122663
    - Calcular velocidade correta -> passed @LEI-122663

* [x] C5 CarrackTest
  tags: unit-tests, test-cases
    - Criar Carrack decks -> passed @LEI-122663
    - Verificar número decks -> passed @LEI-122663
    - Validar remoção inválida -> passed @LEI-122663

* [x] C6 FrigateTest
  tags: unit-tests, test-cases
    - Criar Frigate armada -> passed @LEI-122663
    - Testar disparo canhões -> passed @LEI-122663
    - Confirmar estado combate -> passed @LEI-122663

* [x] C7 GalleonTest
  tags: unit-tests, test-cases
    - Inicializar Galleon -> passed @LEI-122663
    - Atribuir tripulação -> passed @LEI-122663
    - Calcular alcance vento -> passed @LEI-122663

### S4 Task test case
* [x] C8 TaskTest
  tags: unit-tests, test-cases
    - Criar nova tarefa -> passed @LEI-122663
    - Marcar como concluída -> passed @LEI-122663
    - Validar dependências -> passed @LEI-122663
