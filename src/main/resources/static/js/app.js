document.addEventListener('DOMContentLoaded', () => {
  
  // Elementos do DOM
  const kpiTotalAlunos = document.getElementById('kpi-total-alunos');
  const kpiVencendosSemana = document.getElementById('kpi-vencendo-semana');
  const tabelaVencimentos = document.getElementById('tabela-vencimentos');
  const btnAtualizar = document.getElementById('btn-atualizar-mensalidades');
  const dataHojeSpan = document.getElementById('data-hoje');

  // Formatar data de hoje para o topo do card da agenda
  const hoje = new Date();
  dataHojeSpan.textContent = hoje.toLocaleDateString('pt-BR');

  // 1. Carregar Alunos e atualizar KPI
  async function carregarAlunos() {
    try {
      const alunos = await API.listarAlunos();
      kpiTotalAlunos.textContent = alunos.length;
    } catch (err) {
      console.error(err);
      kpiTotalAlunos.textContent = '!';
    }
  }

  // 2. Carregar Mensalidades da Semana
  async function carregarVencimentosSemana() {
    try {
      const mensalidades = await API.listarVencendosNaSemana();
      
      kpiVencendosSemana.textContent = mensalidades.length;
      tabelaVencimentos.innerHTML = '';

      if (mensalidades.length === 0) {
        tabelaVencimentos.innerHTML = `<tr><td colspan="5" class="text-center">Nenhuma mensalidade vencendo esta semana.</td></tr>`;
        return;
      }

      mensalidades.forEach(m => {
        const tr = document.createElement('tr');
        
        // Tratar dados
        const nomeAluno = m.aluno ? m.aluno.nome : `Aluno ID: ${m.alunoId || '-'}`;
        const dataVenc = new Date(m.dataVencimento).toLocaleDateString('pt-BR', { timeZone: 'UTC' });
        const valorFormatado = m.valor ? `R$ ${m.valor.toFixed(2)}` : 'R$ 0,00';
        const status = m.status || 'PENDENTE';

        tr.innerHTML = `
          <td><strong>${nomeAluno}</strong></td>
          <td>${dataVenc}</td>
          <td>${valorFormatado}</td>
          <td><span class="status-badge status-${status}">${status}</span></td>
          <td>
            ${status !== 'PAGO' 
              ? `<button class="btn-sm-pay" onclick="darBaixa(${m.id})">Baixar</button>` 
              : '✅'
            }
          </td>
        `;
        tabelaVencimentos.appendChild(tr);
      });

    } catch (err) {
      console.error(err);
      tabelaVencimentos.innerHTML = `<tr><td colspan="5" class="text-center" style="color:red;">Erro ao carregar dados.</td></tr>`;
    }
  }

  // Função Global para ação de Baixa na Mensalidade
  window.darBaixa = async (id) => {
    if (confirm('Confirmar o recebimento desta mensalidade?')) {
      try {
        await API.registrarPagamento(id);
        carregarVencimentosSemana(); // Recarrega os dados
      } catch (err) {
        alert('Falha ao registrar o pagamento.');
      }
    }
  };

  // Event Listeners
  btnAtualizar.addEventListener('click', carregarVencimentosSemana);

  // Inicialização do Dashboard
  carregarAlunos();
  carregarVencimentosSemana();

  // Polling em tempo real (Atualiza a cada 10 segundos)
  setInterval(() => {
    carregarAlunos();
    carregarVencimentosSemana();
  }, 10000);

});