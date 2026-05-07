package com.example.tutoria03.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutoria03.models.Estudiante;
import com.example.tutoria03.repositories.IEstudianteRepository;

@Service
public class EstudianteService {
    
    // crear una instancia de repository
    @Autowired
    private IEstudianteRepository estudianteRepository;

    public ArrayList<Estudiante> GetAll(){
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
    }

    public Estudiante save(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public Estudiante update(Estudiante estudiante){
        return buscarEstudiantePorId(estudiante.getId())
                .map(existing -> estudianteRepository.save(estudiante))
                .orElse(null);
    }

    public boolean delete(int id){
        var estudiante = buscarEstudiantePorId(id);

        if(estudiante.isPresent()){
            estudianteRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private Optional<Estudiante> buscarEstudiantePorId(int id){
        return estudianteRepository.findById(id);
    }

}
