package com.example.tutoria03.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutoria03.models.Estudiante;
import com.example.tutoria03.repositories.IEstudianteRepository;

@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;

    public ArrayList<Estudiante> GetAll(){
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
    }

    public Estudiante save(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    //  Método unificado para consultar si existe el estudiante
    //    (reutilizado en update y en Delete)
    private Estudiante findEstudianteById(int id){
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante update(Estudiante estudiante){
        // Ahora usa el método unificado
        Estudiante existeEstudiante = findEstudianteById(estudiante.getId());

        if(existeEstudiante != null){
            return estudianteRepository.save(estudiante);
        } else {
            return null;
        }
    }

    //  Lógica de Delete implementada
    public boolean delete(int id){
        Estudiante existeEstudiante = findEstudianteById(id);

        if(existeEstudiante != null){
            estudianteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
