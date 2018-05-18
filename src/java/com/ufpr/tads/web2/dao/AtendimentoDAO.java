package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.utils.DataUtil;

public class AtendimentoDAO {

	private final String SELECT_ALL = "SELECT * FROM tb_atendimento;";
	private final String SELECT = "SELECT * FROM tb_atendimento WHERE id_atendimento= ?;";
	private final String INSERT = "INSERT INTO tb_atendimento(dt_hr_atendimento, dsc_atendimento, "
			+ "id_produto, id_tipo_atendimento, id_usuario, id_cliente, res_atendimento) VALUES (?,?,?,?,?,?,?);";
	private final String RESOLVE = "UPDATE tb_atendimento SET res_atendimento = 'S' WHERE id_atendimento = ?;";
	
	Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Atendimento> listarAtendimentos() throws InstantiationException, IllegalAccessException, SQLException{
	    List<Atendimento> lista = new ArrayList<Atendimento>();
    	try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
            	Atendimento at = new Atendimento();
                at.setIdAtendimento((rs.getInt(1)));
                at.setDataHoraAtendimento(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(2)));
                at.setDescricaoAtendimento(rs.getString(3));
                at.setProduto(new ProdutoDAO().buscarProduto(rs.getInt(4)));
                at.setTipoAtendimento(new TipoAtendimentoDAO().buscarTipoAtendimento(rs.getInt(5)));
                at.setUsuario(new UsuarioDAO().buscarUsuario(rs.getInt(6)));
                at.setCliente(new ClienteDAO().buscarCliente(rs.getInt(7)));
                at.setResultadoAtendimento(rs.getString(8));
                lista.add(at);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
    }

    public Atendimento buscarAtendimento(int id) {
    	Atendimento at = new Atendimento();
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(SELECT);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                	at.setIdAtendimento((rs.getInt(1)));
                    at.setDataHoraAtendimento(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(2)));
                    at.setDescricaoAtendimento(rs.getString(3).trim());
                    at.setProduto(new ProdutoDAO().buscarProduto(rs.getInt(4)));
                    at.setTipoAtendimento(new TipoAtendimentoDAO().buscarTipoAtendimento(rs.getInt(5)));
                    at.setUsuario(new UsuarioDAO().buscarUsuario(rs.getInt(6)));
                    at.setCliente(new ClienteDAO().buscarCliente(rs.getInt(7)));
                    at.setResultadoAtendimento(rs.getString(8));
                    }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {con.close();} catch (SQLException e) {}
            }
            return at;
    }
    
	public void adicionarAtendimento(Atendimento atendimento) throws InstantiationException, IllegalAccessException, SQLException {
    	try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(INSERT);
	        stmt.setTimestamp(1, DataUtil.formataDataHoraBeanParaSql(atendimento.getDataHoraAtendimento()));
	        stmt.setString(2, atendimento.getDescricaoAtendimento());
	        stmt.setInt(3, atendimento.getProduto().getIdProduto());
	        stmt.setInt(4, atendimento.getTipoAtendimento().getIdTipoAtendimento());
	        stmt.setInt(5, atendimento.getUsuario().getId());
	        stmt.setInt(6, atendimento.getCliente().getIdCliente());
	        stmt.setString(7, atendimento.getResultadoAtendimento());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }

	}

	public void resolverAtendimento(int idAtendimento) throws InstantiationException, IllegalAccessException, SQLException {
		try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(RESOLVE);
	        stmt.setInt(1, idAtendimento);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
	}

}
