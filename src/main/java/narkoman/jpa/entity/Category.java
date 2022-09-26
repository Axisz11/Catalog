package narkoman.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // mappedBy - это название поля в ссылаемой сущности которая ссылается на исходную сущность.

    @OneToMany(mappedBy = "category")
    private List<Product> products;


    @OneToMany(mappedBy = "category")
    private List<Option> options;

    @Column(name = "category_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String getName() {
        return name;
    }
}

// Category -> (Product), (Value), (Option)

// Product -> (Value),

// Option -> (Category), (Value)

// Value -> (Product), Option