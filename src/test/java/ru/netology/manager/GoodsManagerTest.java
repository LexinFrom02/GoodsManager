package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class GoodsManagerTest {
    private ProductRepository repository = new ProductRepository();
    private GoodsManager manager = new GoodsManager(repository);

    private Product firstProduct = new Smartphone(1, "Iphone", 1000, "Apple");
    private Product secondProduct = new Smartphone(2, "Nokia", 600, "Nokia inc.");
    private Product thirdProduct = new Book(3, "Poor Dad, rich Dad", 100, "Kiyosaki");
    private Product fouthProduct = new Smartphone(4, "War and peace", 150, "Tolstoy");

    @BeforeEach
    public void setUp() {
        manager.add(firstProduct);
        manager.add(secondProduct);
        manager.add(thirdProduct);
        manager.add(fouthProduct);
    }

    @Test
    public void shouldAdd() {
        Product[] actual = repository.getAll();
        Product[] expected = new Product[]{firstProduct, secondProduct, thirdProduct, fouthProduct};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByName() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = new Product[]{secondProduct};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAuthor() {
        Product[] actual = manager.searchBy("Kiyosaki");
        Product[] expected = new Product[]{thirdProduct};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByProducer() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{firstProduct};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchThenNoMatches() {
        Product[] actual = manager.searchBy("Motorolla");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteById() {
        repository.removeById(2);
        Product[] actual = repository.getAll();
        Product[] expected = new Product[]{firstProduct,thirdProduct, fouthProduct};
        assertArrayEquals(expected, actual);
    }
}