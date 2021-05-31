package com.example.challenge2_alkemy.controller;

import com.example.challenge2_alkemy.model.Blogs;
import com.example.challenge2_alkemy.service.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UserController {
    //Clase donde se van a controlar los Endpoints de las acciones que puede realizar un usuario una vez logueado

    @Autowired
    private OperationService oService;

    //Endpoint para mostrar todas los blogs creados por el usuario
    @GetMapping("/")
    public String welcomeUser(Model model){
        model.addAttribute("listBlog", oService.getByAll());
        return "homeAuth";
    }

    //Endpoint para registrar un nuevo blog
    @GetMapping("/createBlog")
    public String createBlog(Model model){
        model.addAttribute("blog", new Blogs());
        return "formBlog";
    }

    //Endpoint para guardar un nuevo blog, Retorna por consola los datos en formato JSON
    @PostMapping("/saveBlog")
    public String saveBlog(@Validated @ModelAttribute Blogs b, BindingResult result, Model model, RedirectAttributes redirect){
        if (result.hasErrors()) {
            System.out.println("Problems with date");
            return "redirect:/auth/createBlog";
        }
        else{
            oService.saveBlog(b);
            System.out.println("{Id: "+ b.getId()+", Title: "+ b.getTitle()+", Image: "+ b.getImage()+", Category: "
            + b.getCategory()+", CreationDate: "+ b.getCreationDate() + ";}");
            redirect.addFlashAttribute("success", "New registered blog!");
        }
        return "redirect:/auth/";
    }

    //Endpoint para buscar por titulo un blog, Retorna por consola los datos en formato JSON
    @GetMapping("/posts/title")
    public String searchBlogTitle(@RequestParam(required = false) String title, Model model,@ModelAttribute("blog") Blogs blogs){
        model.addAttribute("search", "Search Blogs for Title");
        model.addAttribute("listBlog", oService.searchTitle(title));
        for (Blogs blog : oService.searchTitle(title)){
            System.out.println("{'id': "+ blog.getId()+", 'title': "+ blog.getTitle()+", 'image': "+ blog.getImage()+", 'category': "
            + blog.getCategory()+", 'creationDate': "+ blog.getCreationDate() + ";}");
        }
        return "postsTitle";
    }

    //Endpoint para buscar por categoria un blog, Retorna por consola los datos en formato JSON
    @GetMapping("/posts/category")
    public String searchBlogCategory(@RequestParam(required = false) String category, Model model, @ModelAttribute("blog") Blogs blogs){
        model.addAttribute("search", "Search Blogs for Category");
        model.addAttribute("listBlog", oService.searchCategory(category));
        for (Blogs blog : oService.searchCategory(category)){
            System.out.println("{'id': "+ blog.getId()+", 'title': "+ blog.getTitle()+", 'image': "+ blog.getImage()+", 'category': "
            + blog.getCategory()+", 'creationDate': "+ blog.getCreationDate() + ";}");
        }
        return "postsCategory";
    }

    //Endpoint para buscar por id un blog, Retorna por consola los datos en formato JSON
    @GetMapping("/posts/id")
    public String searchBlogId(@RequestParam(required = false) Long id, Model model, @ModelAttribute("blog") Blogs blogs){
        model.addAttribute("search", "Search Blogs for Id");
        model.addAttribute("listBlog", oService.searchId(id));
        return "postsId";
    }

    //Endpoint para BORRAR un blog por id
    @GetMapping("/delete/{id}")
    public String deleteBlogId(@PathVariable("id") Long id, RedirectAttributes redirect){
        try {
            if (id>0){
                Blogs b = oService.searchId(id);
                if (b != null) {
                    oService.deleteId(id);
                    redirect.addFlashAttribute("warning", "Successfully deleted.");
                    return "redirect:/auth/";
                }
                else{
                    redirect.addFlashAttribute("error", "ERROR: The id does not exist");
                }
            }
        } catch (Exception e){
            System.out.println("Problems with the id");
            System.out.println(e.getMessage());
        }
        return "redirect:/auth/";
    }

    //Endpoint para ACTUALIZAR un blog por id
    @GetMapping("/edit/{id}")
    public String pathBlodId(@PathVariable("id") Long id, Model model, RedirectAttributes redirect){
        try {
            if (id>0) {
                Blogs blog = oService.searchId(id);
                if (blog != null) {
                    model.addAttribute("blog", blog);
                    return "formBlog";
                }
                else{
                    redirect.addFlashAttribute("error", "ERROR: The id does not exist");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/auth/";
    }
}
