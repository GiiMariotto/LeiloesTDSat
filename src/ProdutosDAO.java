import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    // Método para cadastrar produto
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB(); // conecta ao banco

        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            int result = prep.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.");
            }

            prep.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }

    // Método para listar todos os produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conn = new conectaDAO().connectDB(); // conecta ao banco

        try {
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

            resultset.close();
            prep.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }

        return listagem;
    }

    // Método para vender um produto (atualiza status)
    public void venderProduto(int id) {
        conn = new conectaDAO().connectDB(); // conecta ao banco

        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            int result = prep.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado.");
            }

            prep.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
        }
    }
    // Método para listar apenas produtos vendidos
public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conn = new conectaDAO().connectDB();

    try {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

        resultset.close();
        prep.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }

    return listagem;
}

}

