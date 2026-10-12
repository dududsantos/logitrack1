import { useEffect, useState } from 'react'
import { api } from '../api'

function Dashboard() {
  const [dados, setDados] = useState(null)
  const [veiculos, setVeiculos] = useState([])
  const [veiculoId, setVeiculoId] = useState('')

  useEffect(() => {
    api('/veiculos').then(setVeiculos)
  }, [])

  // Recarrega sempre que o filtro de veículo muda
  useEffect(() => {
    api('/dashboard' + (veiculoId ? '?veiculoId=' + veiculoId : '')).then(setDados)
  }, [veiculoId])

  if (!dados) return <p className="legenda">Carregando...</p>

  return (
    <>
      <div className="titulo-pagina">
        <h2>Dashboard</h2>
        <p>Indicadores da frota, viagens e manutenções</p>
      </div>

      <div className="indicadores">
        <section className="card indicador">
          <div className="card-topo">
            <h3>Total de KM percorridos</h3>
            <select value={veiculoId} onChange={(e) => setVeiculoId(e.target.value)}>
              <option value="">Toda a frota</option>
              {veiculos.map((v) => (
                <option key={v.id} value={v.id}>
                  {v.placa} - {v.modelo}
                </option>
              ))}
            </select>
          </div>
          <p className="numero">{formatarNumero(dados.totalKm)} km</p>
          <p className="legenda">Soma da quilometragem das viagens</p>
        </section>

        <section className="card indicador">
          <h3>Projeção financeira do mês</h3>
          <p className="numero">{formatarMoeda(dados.projecaoFinanceiraMes)}</p>
          <p className="legenda">Custo estimado das manutenções do mês atual</p>
        </section>
      </div>

      <div className="graficos">
        <section className="card">
          <h3>Viagens realizadas por categoria</h3>
          <GraficoBarras
            itens={dados.volumePorCategoria.map((c) => ({
              rotulo: c.tipo,
              valor: c.total,
              texto: c.total + (c.total === 1 ? ' viagem' : ' viagens'),
            }))}
          />
        </section>

        <section className="card">
          <h3>Ranking de utilização (KM)</h3>
          <GraficoBarras
            itens={dados.rankingUtilizacao.map((r, i) => ({
              rotulo: i + 1 + 'º ' + r.modelo,
              valor: Number(r.totalKm),
              texto: formatarNumero(r.totalKm) + ' km',
            }))}
          />
        </section>
      </div>

      <section className="card">
        <h3>Próximas manutenções</h3>
        <div className="tabela">
          <table>
            <thead>
              <tr><th>Data</th><th>Veículo</th><th>Serviço</th><th>Custo</th><th>Status</th></tr>
            </thead>
            <tbody>
              {dados.proximasManutencoes.map((m) => (
                <tr key={m.id}>
                  <td>{new Date(m.dataInicio + 'T00:00').toLocaleDateString('pt-BR')}</td>
                  <td><span className="placa">{m.placa}</span>{m.modelo}</td>
                  <td>{m.tipoServico}</td>
                  <td>{formatarMoeda(m.custoEstimado)}</td>
                  <td><span className={'status status-' + m.status}>{formatarStatus(m.status)}</span></td>
                </tr>
              ))}
              {dados.proximasManutencoes.length === 0 && (
                <tr><td colSpan="5" className="vazio">Nenhuma manutenção agendada.</td></tr>
              )}
            </tbody>
          </table>
        </div>
      </section>
    </>
  )
}


function GraficoBarras({ itens }) {
  if (itens.length === 0) return <p className="legenda">Sem dados.</p>

  const maior = Math.max(...itens.map((i) => i.valor))

  return (
    <div className="grafico">
      {itens.map((item) => (
        <div className="grafico-linha" key={item.rotulo} title={item.rotulo + ': ' + item.texto}>
          <span className="grafico-rotulo">{item.rotulo}</span>
          <div className="grafico-trilho">
            <div className="grafico-barra" style={{ width: maior > 0 ? (item.valor / maior) * 100 + '%' : 0 }} />
          </div>
          <span className="grafico-valor">{item.texto}</span>
        </div>
      ))}
    </div>
  )
}

function formatarNumero(valor) {
  return Number(valor).toLocaleString('pt-BR', { maximumFractionDigits: 2 })
}


function formatarStatus(status) {
  const nomes = { PENDENTE: 'Pendente', EM_REALIZACAO: 'Em realização', CONCLUIDA: 'Concluída' }
  return nomes[status] || status
}

function formatarMoeda(valor) {
  return Number(valor).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })
}

export default Dashboard
