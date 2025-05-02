package com.christian.ecommerce.repository;

import com.christian.ecommerce.dao.CategoryDAO;
import com.christian.ecommerce.model.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FakeRepository implements CategoryDAO {

    private List<Category> categories = new ArrayList<>( List.of(new Category(1, "Game"),
                                        new Category(2, "Kitchen"),
                                        new Category(3, "Technology")));


    public List<Category> findAllByOrderByNameAsc() {
        return categories.stream().sorted(Comparator.comparing(category -> category.getName()))
                .toList();
    }

    public Optional<Category> findById(Integer id){
        return categories.stream().filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Category save(Category category){
        Category found = findById(category.getId()).get();

        if (found != null){
            int index = categories.indexOf(found);
            categories.set(index, category);

            return category;
        }

        categories.add(category);
        return category;
    }

    public void deleteById(Integer id){
        Category byId = findById(id).get();

        if (byId != null){
            categories.remove(id);
        }
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
