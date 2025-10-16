package mvvm.sistemadeprodutos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvvm.sistemadeprodutos.model.Produto;

public class ProdutoService {
    private Map<Long, Produto> banco = new HashMap<>();
    private Long idGenerator = 1L; // Use L para Long

    public List<Produto> listarProdutos(){
        return new ArrayList<>(banco.values());
    }

    public Produto buscarPorId(Long id){
        return banco.get(id);
    }

    public boolean salvar(Produto produto){
        if (produto.getId() == null) {
            produto.setId(idGenerator++);
        }
        banco.put(produto.getId(), produto);
        return true;
    }

    public boolean atualizar(Produto produto){
     
        if (produto.getId() != null && banco.containsKey(produto.getId())) {
            banco.put(produto.getId(), produto);
            return true;
        }
        return false;
    }

    public boolean excluir(Long id){
        return banco.remove(id) != null;
    }
}
