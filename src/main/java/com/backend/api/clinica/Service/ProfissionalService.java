package com.backend.api.clinica.Service;

import com.backend.api.clinica.DTOS.RequestDTO.ProfissionalRequest;
import com.backend.api.clinica.DTOS.ResponseDTO.ProfissionalResponse;
import com.backend.api.clinica.DTOS.UpdateDTO.AtualizarDados;
import com.backend.api.clinica.DTOS.UpdateDTO.AtualizarSenha;
import com.backend.api.clinica.Entity.Profissional;
import com.backend.api.clinica.Mapper.ProfissionalMapper;
import com.backend.api.clinica.Repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository repository;
    private final PasswordEncoder encoder;
    private final ProfissionalMapper mapper;

    public Profissional buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    //Transactional serve para ou fazer uma operação por completa ou não fazer ela
    @Transactional

    //Metodo de cadastrar profissional que vai retornar um response
    //Corpo da função vai receber um request
    public ProfissionalResponse cadastrarProfissional(ProfissionalRequest request){
        //Aqui transforma o request que ta vindo em uma entidade do meu banco e
        //joga na variavel profissional
        Profissional profissional = mapper.toEntity(request);

        //Modifica a senha para fazer um hash e criptografar ela
        profissional.setSenha(encoder.encode(request.senha()));

        //Repository salva profissional, agora na entity tem id, nome, email e senha hasheada
        repository.save(profissional);

        //Retorna o mapper que tranformar a entidade em um response
        //Mandando so id, nome e email
        return mapper.toResponse(profissional);

    }

    public List<ProfissionalResponse> buscarProfissionais(){
        //Retorna o findAll onde mostra todos os profissionais
        //Stream serve para fazer operações
        //.map é para transformar cada coisa que veio do stream em um tipo ProfissionalResponse
        //toList é para listar
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProfissionalResponse buscarProfissionalPorId(Long id){
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com não foi encontrado"));
    }

    @Transactional
    public ProfissionalResponse atualizarProfissional(Long id, AtualizarDados dto){
        Profissional profissional = buscarPorId(id);

        mapper.updateEntityFromDto(dto, profissional);

        return mapper.toResponse(profissional);
    }

    @Transactional
    public void atualizarSenhaProfissional(Long id, AtualizarSenha dto){
        Profissional profissional = buscarPorId(id);

        if(!encoder.matches(dto.senhaAtual(), profissional.getSenha())){
            throw new IllegalArgumentException("Senha atual está inválida");
        }

        profissional.setSenha(encoder.encode(dto.novaSenha()));

    }

    @Transactional
    public void desativarProfissional(Long id){
        //Buscando profissional (lança a exceção descrita mais pra cima)
        Profissional profissional = buscarPorId(id);

        //Alterar o status do profissional que ele achou pelo ID para inativo
        profissional.setAtivo(false);

        //Salva a alteração (Faz um UPDATE no banco de dados)
        repository.save(profissional);
    }
}
