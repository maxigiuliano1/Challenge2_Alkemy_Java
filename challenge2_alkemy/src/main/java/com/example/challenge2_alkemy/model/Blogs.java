package com.example.challenge2_alkemy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blogs implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;
    private String title;
    private String content;
    private String image;
    private String category; //esto puedo reemplazar por una clase categoria que contenga varias categorias
    private Date creationDate; //en caso de tener problemas con el retorno del tipo date en thymeleaf cambiarlo por tipo string

    //Relacionarlo con el atributo id de la clase user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users idUCreator;
    /**
     * 
     */
    public Blogs() {
        super();
    }
    /**
     * @param id
     * @param title
     * @param content
     * @param image
     * @param category
     * @param creationDate
     * @param idUCreator
     */
    public Blogs(Long id, String title, String content, String image, String category, Date creationDate,
            Users idUCreator) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.creationDate = creationDate;
        this.idUCreator = idUCreator;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * @return the idUCreator
     */
    public Users getIdUCreator() {
        return idUCreator;
    }
    /**
     * @param idUCreator the idUCreator to set
     */
    public void setIdUCreator(Users idUCreator) {
        this.idUCreator = idUCreator;
    }

    private static final long serialVersionUID = 1L;    
}
