package com.example.challenge2_alkemy.service;

import java.util.Collections;
import java.util.List;

import com.example.challenge2_alkemy.model.Blogs;
import com.example.challenge2_alkemy.model.Users;
import com.example.challenge2_alkemy.repositories.BlogsRepository;
import com.example.challenge2_alkemy.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class OperationService {
    @Autowired
    private UsersRepository uRepository;

    @Autowired
    private BlogsRepository bRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Metodo que guarda un usuario en la base de datos
    public Users saveUser(Users u){
        u.setPassword(passwordEncoder.encode(u.getPassword())); //aca encriptaria la contrasena con bcrypasswordencoder
        return uRepository.save(u);
    }

    //Metodo que guarda un blog en la base de datos
    public void saveBlog(Blogs b){
        bRepository.save(b);
    }

    //Metodo que busca un blog por :id
    public Blogs searchId(Long id){
        try {
            if(bRepository.existsById(id)){
                return bRepository.getById(id); //Una vez obtenido el objeto Blog, atraves del controller usuario logueado muestro los detalles que me piden del blog por thymeleaf
            }
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
            System.out.println("blog id does not exist");
        }
        return null;
    }
    
    //Metodo que busca un blog por categoria :category
    public List<Blogs> searchCategory(String category){
        try {
            if (bRepository.findByCategory(category) != null) {
                return bRepository.findByCategory(category);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("blog category does not exist");
        }
        return Collections.emptyList();
    }

    //Metodo que busca un blog por titulo :title
    public List<Blogs> searchTitle(String title){
        try {
            if (bRepository.findByTitle(title)!= null) {
                return bRepository.findByTitle(title);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("blog title does not exist");
        }
        return Collections.emptyList();
    }

    //Metodo para borrar un blog por :id
    public void deleteId(Long id){
        try {
            if (bRepository.existsById(id)) {
                bRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
            System.out.println("blog id does not exist, cannot be delete");
        }
    }

    //Metodo para obtener la lista de blogs
    public List<Blogs> getByAll(){
        try {
            if(!bRepository.findAll().isEmpty()) {
                return bRepository.findAll();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("List is empty, add a blog");
        }
        return Collections.emptyList();
    }

    //Metodo para ordenar por fecha de creacion

    //Metodo para validar URL de imagen
}
