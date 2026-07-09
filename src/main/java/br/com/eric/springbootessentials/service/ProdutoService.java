package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.database.model.ProdutoEntity;
import br.com.eric.springbootessentials.dto.ProdutoDto;
import br.com.eric.springbootessentials.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<>();

    static
    {
        PRODUTOS.add(new ProdutoEntity(
                1,
                "Iphone",
                new BigDecimal(7000),
                10));

        PRODUTOS.add(new ProdutoEntity(
                2,
                "Notebook",
                new BigDecimal(5000),
                10));

        PRODUTOS.add(new ProdutoEntity(
                3,
                "Mouse",
                new BigDecimal(500),
                10));
    }

    public List<ProdutoEntity> findAll ()
    {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoEntity createProduct (ProdutoDto produtoDto)
    {
        Integer identify = PRODUTOS.stream()
                .mapToInt(ProdutoEntity::getId)
                .max()
                .orElse(0) + 1;

         ProdutoEntity newProduct = new ProdutoEntity(
                 identify,
                 produtoDto.getNome(),
                 produtoDto.getPreco(),
                 produtoDto.getQuantidade()
         );

         PRODUTOS.add(newProduct);
         return newProduct;
    }

    public ProdutoEntity updateProduct(ProdutoDto produtoDto, Integer id) throws NotFoundException {
        ProdutoEntity produto = PRODUTOS.stream().
                filter(p -> p.getId().equals(id)).
                findAny().
                orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidade(produtoDto.getQuantidade());

        return produto;
    }

    public void deleteProduct (Integer id)
    {
        PRODUTOS.removeIf(produto -> produto.getId().equals(id));
    }
}
