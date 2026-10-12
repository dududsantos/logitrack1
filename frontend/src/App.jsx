import { useState } from 'react'
import { getToken, removerToken } from './api'
import Login from './pages/Login'
import Viagens from './pages/Viagens'
import Dashboard from './pages/Dashboard'

function App() {
  const [logado, setLogado] = useState(!!getToken())
  const [pagina, setPagina] = useState('viagens')

  if (!logado) {
    return <Login onLogin={() => setLogado(true)} />
  }

  function sair() {
    removerToken()
    setLogado(false)
  }

  return (
    <>
      <header className="menu">
        <strong className="marca">
          <span className="logo">LP</span>
          <span>LogiTrack Pro</span>
        </strong>
        <nav>
          <button className={pagina === 'viagens' ? 'ativo' : ''} onClick={() => setPagina('viagens')}>
            Viagens
          </button>
          <button className={pagina === 'dashboard' ? 'ativo' : ''} onClick={() => setPagina('dashboard')}>
            Dashboard
          </button>
        </nav>
        <button onClick={sair}>Sair</button>
      </header>

      <main className="conteudo">
        {pagina === 'viagens' ? <Viagens /> : <Dashboard />}
      </main>
    </>
  )
}

export default App
