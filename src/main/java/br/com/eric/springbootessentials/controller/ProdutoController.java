package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.database.model.ProdutoEntity;
import br.com.eric.springbootessentials.dto.ProdutoDto;
import br.com.eric.springbootessentials.exception.NotFoundException;
import br.com.eric.springbootessentials.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoEntity> findAll ()
    {
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity createProduct (@RequestBody ProdutoDto produtoDto)
    {
        return produtoService.createProduct(produtoDto);
    }

    @PutMapping("/{id}")
    public ProdutoEntity updateProduct (@PathVariable Integer id, @RequestBody ProdutoDto produtoDto) throws NotFoundException {
        return produtoService.updateProduct(produtoDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct (@PathVariable Integer id)
    {
        produtoService.deleteProduct(id);
    }
}
