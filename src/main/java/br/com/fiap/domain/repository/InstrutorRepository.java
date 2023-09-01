package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Instrutor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class InstrutorRepository implements Repository<Instrutor, Long> {

    private Set<Instrutor> instrutors;

    private static volatile InstrutorRepository instance;



    public InstrutorRepository() {
        instrutors = new LinkedHashSet<>();
    }

    public static InstrutorRepository of(){
        InstrutorRepository result = instance;
        if (Objects.nonNull(result)){
            return result;
        }
        synchronized (InstrutorRepository.class){
            if (Objects.isNull(null)){
                instance = new InstrutorRepository();
            }
            return instance;
        }
    }

    @Override
    public List<Instrutor> findAll() {
        return instrutors.stream().toList();
    }

    @Override
    public Instrutor findById(Long id) {
        return instrutors.stream().filter(a ->a.getId().equals(id)).findFirst().orElse(null) ;
    }

    @Override
    public List<Instrutor> findByName(String texto) {
        return instrutors.stream().filter(a->a.getNome().toLowerCase().contains(texto.toLowerCase())).toList();
    }

    @Override
    public Instrutor persist(Instrutor instrutor) {
        if (Objects.isNull(instrutor)) return null;
        if (Objects.isNull(instrutor.getId())) instrutor.setId(instrutors.size() + 1L);
        instrutors.add(instrutor);
        return instrutor;
    }
}
