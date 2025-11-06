package cat.copernic.llibreria2.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

/**
 * @Entity: taula BOOKS
 */
@Entity

/**
 * Índex únic per ISBN
 */
@Table(
        name = "books",
        indexes = @Index(
                name = "ix_book_isbn",
                columnList = "isbn",
                unique = true
        )
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sense validacions de presentació; només NOT NULL/length a BD
    @Column(nullable = false, length = 180)
    private String title;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    // DECIMAL(12,2) a BD
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookFormat format;

    // Opcionalment nullable a BD
    private LocalDate publishedAt;

    /**
     * Molts llibres -> un autor (FK NOT NULL)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate p) {
        this.publishedAt = p;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
