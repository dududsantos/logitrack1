import { useState } from 'react'
import { api, salvarToken } from '../api'

function Login({ onLogin }) {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [erro, setErro] = useState('')

  async function entrar(e) {
    e.preventDefault()
    setErro('')
    try {
      const resposta = await api('/auth/login', {
        method: 'POST',
        body: JSON.stringify({ username, password }),
      })
      salvarToken(resposta.token)
      onLogin()
    } catch {
      setErro('Usuário ou senha inválidos')
    }
  }

  return (
    <div className="login-pagina">
      <form className="login" onSubmit={entrar}>
        <div className="marca">
          <div className="logo">L</div>
          <h1>LogiTrack Pro</h1>
          <p>Gestão de frota e viagens</p>
        </div>

        <label>
          Usuário
          <input value={username} onChange={(e) => setUsername(e.target.value)} required />
        </label>

        <label>
          Senha
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </label>

        {erro && <p className="erro">{erro}</p>}

        <button type="submit">Entrar</button>
      </form>
    </div>
  )
}

export default Login
