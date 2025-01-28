package br.com.ti0523.patrimonio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ti0523.patrimonio.pojo.Patrimonio;

public class DAOPatrimonio 
{
	
	public String cadastrar(Patrimonio patrimonio)
	{
		
		String resp = null;
		
		// variavel para a conexao com o banco de dados
		Connection con = null;
		
		//para executar as consultas sql vamos usar o comando PreoaredStatment ligadp a uma variavel
		PreparedStatement pst = null;
		
		try 
		{
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//passagem da url de conexão com o banco de dados
			//nome,usuario,senha,e porta
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "");
			
			//criar uma variavel para inserir os dados no banco
			String consulta = "insert into tbl_patrimonio(serie,nomepatrimonio,tipo,descricao,localizacao,datafabricacao,dataaquisicao);values(?,?,?,?,?,?,?)";
			
			//preparar a consulta para ser executada(colocar em memoria)
			pst = con.prepareStatement(consulta);
			
			// passagem dos dados aos parametros
			pst.setString(1, patrimonio.getSerie());
			pst.setString(2, patrimonio.getNomepatrimonio());
			pst.setString(3, patrimonio.getTipo());
			pst.setString(4, patrimonio.getDescricao());
			pst.setString(5, patrimonio.getLocalizacao());
			pst.setString(6, patrimonio.getDatafabricacao());
			pst.setString(7, patrimonio.getDataaquisicao());
			
			//executar a consulta de inserção de dados 
			//retornara 0 ou 1, caso seja 0 o retorno, os dados não foram cadastrados
			//caso o retorno seja 1 os dados foram cadastrados
			if(pst.executeUpdate() > 0) 
			{
				resp = "patrimonio cadastrado";
			}
			else
			{
				resp = "patrimonio não cadastrado";
			}			
							
		}
		
		// ira adicionar uma menssagem de err0
		catch(Exception ex)
		{
			resp = "erro ao cadastrar o patrimonio "+ex.getLocalizedMessage();
		}
		
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		return resp;
	}

}
