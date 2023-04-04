package com.arabsoft.pfe.projet.service.framework;

import java.util.List;

import com.arabsoft.pfe.projet.model.framework.ObjetPersistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class GenericService<T extends ObjetPersistant, TData extends JpaRepository<T, Long>> {

    @Autowired
    TData tdata;

    public T save(T t) {
        try {
           return  tdata.save(t);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void delete(Long id) {
        try {
            tdata.deleteById(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T get(Long id) {
        try {
            return tdata.getById(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<T> listAll() {
        try {
            return tdata.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<T> saveMany(List<T> listT) {
        try {
           return tdata.saveAll(listT);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
