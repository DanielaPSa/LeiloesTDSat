/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resulset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto) {
         
        boolean sucesso = false;
        
        try { 
            conn = new conectaDAO().connectDB();
            
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            
            sucesso = true;
            
        } catch ( Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto" + e.getMessage());
            
            sucesso = false;
            
        } finally {
            
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
                
            } catch (Exception e) {
            }
            
        }
        
        return sucesso;
        
    }
    
    public ArrayList<ProdutosDTO> listarProduto(){
        
        listagem = new ArrayList<>();
        
        try{ 
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT * FROM produtos";
            
            prep = conn.prepareStatement(sql);
            resulset = prep.executeQuery();
            
            while (resulset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resulset.getInt("id"));
                produto.setNome(resulset.getString("nome"));
                produto.setValor(resulset.getInt("valor" ));
                produto.setStatus(resulset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            
        }
        
        return listagem;
        
    }
    
            
         
    }
    


            
                
                
                
                
                
                
                
                
               
            
    
    
        


