package cat.copernic.llibreria2.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;   // timestamp creació automàtic
import org.hibernate.annotations.UpdateTimestamp;     // timestamp última modificació

import jakarta.persistence.*; // anotacions JPA

/**
 * @Entity: classe que es mapeja a una taula
 */
@Entity

/**
 * @Table: configurar nom de taula i índexos
 */
@Table(
    name = "authors",
    indexes = @Index(
        name = "ix_author_email",   // nom de l'índex
        columnList = "email",       // columna indexada
        unique = true               // unicitat garantida a BD
    )
)
public class Author {

    /**
     * @Id: clau primària
     * @GeneratedValue: que MySQL la generi (AUTO_INCREMENT)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column: NOT NULL i longitud a BD (sense validacions d’usuari)
     */
    @Column(nullable = false, length = 120)
    private String name;

    /**
     * Única a BD per assegurar que no es repeteixi
     */
    @Column(nullable = false, unique = true, length = 160)
    private String email;

    /**
     * Timestamps gestionats per Hibernate (no cal @Temporal amb Instant)
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    /**
     * Relació 1 -> N: un autor té molts llibres.
     * mappedBy="author" vol dir que la FK és a BOOKS.author_id
     * cascade+orphanRemoval són decisions de domini/persistència (es poden deixar o treure)
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    // Helpers per mantenir la bidireccionalitat en memòria
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    // Getters/Setters
    public Long getId() { return id; }                 public void setId(Long id) { this.id = id; }
    public String getName() { return name; }           public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }         public void setEmail(String email) { this.email = email; }
    public Instant getCreatedAt() { return createdAt; }public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public List<Book> getBooks() { return books; }     public void setBooks(List<Book> books) { this.books = books; }


    public BookFormat getFormat() { return format; }
    public void setFormat(BookFormat format) { this.format = format; }

    public LocalDate getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDate publishedAt) { this.publishedAt = publishedAt; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}