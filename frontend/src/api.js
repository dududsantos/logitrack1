// Endereço da API (pode ser trocado com a variável VITE_API_URL)
const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api'

export function getToken() {
  return localStorage.getItem('token')
}

export function salvarToken(token) {
  localStorage.setItem('token', token)
}

export function removerToken() {
  localStorage.removeItem('token')
}

// Função única para chamar a API: adiciona o token e trata erros
export async function api(caminho, opcoes = {}) {
  const resposta = await fetch(API_URL + caminho, {
    ...opcoes,
    headers: {
      'Content-Type': 'application/json',
      ...(getToken() && { Authorization: 'Bearer ' + getToken() }),
    },
  })

  // Token inválido ou expirado: volta para o login
  if (resposta.status === 401 && caminho !== '/auth/login') {
    removerToken()
    window.location.reload()
    return
  }

  if (!resposta.ok) {
    const erro = await resposta.json().catch(() => ({}))
    throw new Error(erro.message || 'Erro ' + resposta.status)
  }

  // 204 (exclusão) não tem corpo
  return resposta.status === 204 ? null : resposta.json()
}
