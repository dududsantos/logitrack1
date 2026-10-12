import { useEffect, useState } from 'react'
import { api } from '../api'

const formVazio = {
  veiculoId: '',
  dataSaida: '',
  dataChegada: '',
  origem: '',
  destino: '',
  kmPercorrida: '',
}

function Viagens() {
  const [viagens, setViagens] = useState([])
  const [veiculos, setVeiculos] = useState([])
  const [form, setForm] = useState(formVazio)
  const [editandoId, setEditandoId] = useState(null)
  const [erro, setErro] = useState('')

  async function carregar() {
    setViagens(await api('/viagens'))
  }

  useEffect(() => {
    api('/viagens').then(setViagens)
    api('/veiculos').then(setVeiculos)
  }, [])

  function alterar(e) {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  function limpar() {
    setForm(formVazio)
    setEditandoId(null)
    setErro('')
  }

  async function salvar(e) {
    e.preventDefault()
    setErro('')
    try {
      await api(editandoId ? '/viagens/' + editandoId : '/viagens', {
        method: editandoId ? 'PUT' : 'POST',
        body: JSON.stringify(form),
      })
      limpar()
      carregar()
    } catch (err) {
      setErro(err.message)
    }
  }

  function editar(viagem) {
    setEditandoId(viagem.id)
    setErro('')
    setForm({
      veiculoId: viagem.veiculo.id,
      dataSaida: viagem.dataSaida.slice(0, 16),
      dataChegada: viagem.dataChegada.slice(0, 16),
      origem: viagem.origem,
      destino: viagem.destino,
      kmPercorrida: viagem.kmPercorrida,
    })
  }

  async function excluir(id) {
    if (!confirm('Excluir esta viagem?')) return
    try {
      await api('/viagens/' + id, { method: 'DELETE' })
      carregar()
    } catch (err) {
      setErro(err.message)
    }
  }

  return (
    <>
      <div className="titulo-pagina">
        <h2>Viagens</h2>
        <p>Cadastre e acompanhe as viagens da frota</p>
      </div>

      <section className="card">
        <h3>{editandoId ? 'Editar viagem #' + editandoId : 'Nova viagem'}</h3>

        <form className="formulario" onSubmit={salvar}>
          <label>
            Veículo
            <select name="veiculoId" value={form.veiculoId} onChange={alterar} required>
              <option value="">Selecione</option>
              {veiculos.map((v) => (
                <option key={v.id} value={v.id}>
                  {v.placa} - {v.modelo}
                </option>
              ))}
            </select>
          </label>

          <label>
            Saída
            <input type="datetime-local" name="dataSaida" value={form.dataSaida} onChange={alterar} required />
          </label>

          <label>
            Chegada
            <input type="datetime-local" name="dataChegada" value={form.dataChegada} onChange={alterar} required />
          </label>

          <label>
            Origem
            <input name="origem" value={form.origem} onChange={alterar} required />
          </label>

          <label>
            Destino
            <input name="destino" value={form.destino} onChange={alterar} required />
          </label>

          <label>
            KM percorrida
            <input type="number" step="0.01" min="0.01" name="kmPercorrida" value={form.kmPercorrida} onChange={alterar} required />
          </label>

          <div className="acoes">
            <button type="submit">{editandoId ? 'Salvar alterações' : 'Cadastrar'}</button>
            {editandoId && <button type="button" onClick={limpar}>Cancelar</button>}
          </div>
        </form>

        {erro && <p className="erro">{erro}</p>}
      </section>

      <section className="card">
        <h3>Viagens cadastradas</h3>
        <div className="tabela">
          <table>
            <thead>
              <tr>
                <th>Veículo</th>
                <th>Saída</th>
                <th>Chegada</th>
                <th>Origem</th>
                <th>Destino</th>
                <th>KM</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {viagens.map((v) => (
                <tr key={v.id}>
                  <td><span className="placa">{v.veiculo.placa}</span>{v.veiculo.modelo}</td>
                  <td>{formatarData(v.dataSaida)}</td>
                  <td>{formatarData(v.dataChegada)}</td>
                  <td>{v.origem}</td>
                  <td>{v.destino}</td>
                  <td className="numero-celula">{Number(v.kmPercorrida).toLocaleString('pt-BR')}</td>
                  <td className="acoes-celula">
                    <button className="pequeno" onClick={() => editar(v)}>Editar</button>
                    <button className="pequeno perigo" onClick={() => excluir(v.id)}>Excluir</button>
                  </td>
                </tr>
              ))}
              {viagens.length === 0 && (
                <tr>
                  <td colSpan="7" className="vazio">Nenhuma viagem cadastrada.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </section>
    </>
  )
}

function formatarData(data) {
  return new Date(data).toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' })
}

export default Viagens
