package com.backend.api.clinica.Controller;

import com.backend.api.clinica.DTOS.RequestDTO.ProfissionalRequest;
import com.backend.api.clinica.DTOS.ResponseDTO.ProfissionalResponse;
import com.backend.api.clinica.DTOS.UpdateDTO.AtualizarDados;
import com.backend.api.clinica.DTOS.UpdateDTO.AtualizarSenha;
import com.backend.api.clinica.Service.ProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profissionais")
@Tag(name = "Profissionais", description = "Operações relacionadas a profissionais")
public class ProfissionalController {

    private final ProfissionalService service;

    @Operation(summary = "Cria um profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissional criado"),
            @ApiResponse(responseCode = "400", description = "Dados do profissional inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/criarNovoProfissional")
    //Função de registrar profissional que vai retornar meu ProfissionalResponse
    //Recebe o @RequestBody porque precisar informar dados no corpo da requisição
    //Passa ProfissionalRequest que vai ser atribuida a variável request
    //(que ja vem com o response por causa do service)
    public ResponseEntity<ProfissionalResponse> registrarProfissional(@RequestBody @Valid ProfissionalRequest request) {
        //Retorna o status do response entity como criado
        //Body dele recebe o metodo de cadastro do service
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarProfissional(request));
    }

    @Operation(summary = "Lista todos os profissionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/listarProfissionais")
    public ResponseEntity<List<ProfissionalResponse>> listarProfissionais(){
        return ResponseEntity.ok(service.buscarProfissionais());
    }

    @Operation(summary = "Mostra profissional com um ID específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissional encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/listarProfissional/{id}")
    public ResponseEntity<ProfissionalResponse> listarProfissional(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarProfissionalPorId(id));
    }

    @PutMapping("/atualizarProfisisonal/{id}")
    public ResponseEntity<ProfissionalResponse> atualizarProfissional(@PathVariable Long id, @RequestBody @Valid AtualizarDados dto){
        return ResponseEntity.ok(service.atualizarProfissional(id,dto));
    }

    @PutMapping("/atualizarSenha/{id}")
    public ResponseEntity<Void> atualizarSenha(@PathVariable Long id, @RequestBody @Valid AtualizarSenha dto){
        service.atualizarSenhaProfissional(id,dto);

        return ResponseEntity.noContent().build();
    }

    //Aqui a função é PUT porque como vai atualizar o status para inativo, não vai ser delete
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarUsuario(@PathVariable @Valid Long id){
        //Chama o desativar profissional passando o id dele
        service.desativarProfissional(id);

        return ResponseEntity.noContent().build();
    }
}
