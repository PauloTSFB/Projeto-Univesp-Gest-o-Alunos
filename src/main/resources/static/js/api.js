// Usamos caminhos relativos para não depender de domínio/porta fixo
const API = {
  // Buscar lista de alunos
  async listarAlunos() {
    const res = await fetch('/alunos');
    if (!res.ok) throw new Error('Erro ao buscar alunos');
    return await res.json();
  },

  // Buscar mensalidades vencendo na semana
  async listarVencendosNaSemana() {
    const res = await fetch('/mensalidades/vencendo-semana');
    if (!res.ok) throw new Error('Erro ao buscar mensalidades da semana');
    return await res.json();
  },

  // Dar baixa no pagamento de uma mensalidade
  async registrarPagamento(id) {
    const res = await fetch(`/mensalidades/${id}/pagar`, {
      method: 'PUT'
    });
    if (!res.ok) throw new Error('Erro ao registrar pagamento');
    return await res.json();
  }
};